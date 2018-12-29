package com.zlkj.train.service;

import com.zlkj.train.bean.DiskBean;
import com.zlkj.train.bean.FrmSysUser;
import com.zlkj.train.bean.FrontDiskBean;
import com.zlkj.train.bean.SysUser;
import com.zlkj.train.result.Result;
import com.zlkj.train.service.ExportService;
import com.zlkj.train.service.ProgressService;
import com.zlkj.train.utils.Constant;
import com.zlkj.train.utils.FileOrder;
import com.zlkj.train.utils.HttpClientUtil;
import com.zlkj.train.utils.ReadWriteProperties;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.InetAddress;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Auther: zxj
 * @Date: 2018\12\24 0024 16:01
 * @Description:
 */
@Service
public class ExportToolService {

    @Autowired
    private ExportService exportService;

    /**
     * 初始ini化文件，不存在创建ini文件，存在返回已经存在的信息
     *
     * @param filePath
     * @param trainInfo
     * @return Result
     */
    public Result InitFile(HttpServletRequest request, String filePath, String trainInfo, FrmSysUser user) {
        try {
            if (Constant.USE_DB) {
//                HttpSession session = request.getSession();
//                FrmSysUser user = (FrmSysUser) session.getAttribute("userSession");
                String glbm = "";
                String yhdh = "";
                if (user != null) {
                    glbm = user.getGlbm();
                    yhdh = user.getYhdh();
                }
                exportService.writeInitLogtoDb(trainInfo, glbm, yhdh);
            }
            ReadWriteProperties rif = new ReadWriteProperties();
            filePath = filePath.concat(Constant.WRITE_INITNAME);

            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            rif.setProperty(Constant.TRAIN_INFO, trainInfo, filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success("设备初始化成功");
    }

    /**
     * 复制文件传已经上传byte[]的大小给map
     */
    public void copy(FrontDiskBean frontDiskBean) {
        DiskBean diskBean = frontDiskBean.getInfo();
        double fileExportedSize = 0.00;//已经导过的大小 计算实时进度用的
        double filePositionSize = 0.00;// 出现断点用的 每倒完一个文件计算一次
        String targetPath = Constant.WRITEFILETO;
        //源文件文件名
        String fName;
        //根据类车信息查找是否有断点
        File position = Constant.positionMap.get(diskBean.getTrainInfo());
        double totalSize;
        //进度
        double progress;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            Path startingDir = Paths.get(diskBean.getDiskid());
            List<Path> result = new LinkedList<>();
            //读取文件列表
            Files.walkFileTree(startingDir, new FindJavaVisitor(result));
            //断点续传给文件列表排序，并帅选出剩下的文件
            File[] files = FileOrder.orderByDate(result, position);
            totalSize = getTotalSize(diskBean.getDiskid());
            if (position != null) {
                fileExportedSize = Constant.SizeMap.get(diskBean.getTrainInfo());
            }

            for (File p : files) {
                //读取需要复制的文件
                File sourceFile = new File(p.getPath());
                position = sourceFile;
                //替换源文件路径到目标文件夹
                String tarPath = p.getParent().replace(diskBean.getDiskid(), targetPath);
                //创建目标文件文件夹
                File fileTarPath = new File(tarPath);
                if (!fileTarPath.exists()) {
                    fileTarPath.mkdirs();
                }
                //源文件文件名
                fName = p.getName();

                //拼接目标文件路径和文件名
                tarPath = tarPath.concat("\\").concat(fName);
                //创建目标文件
                File targetFile = new File(tarPath);
                if (!targetFile.exists()) {
                    targetFile.createNewFile();
                }
                //io流固定格式
                bis = new BufferedInputStream(new FileInputStream(sourceFile));
                bos = new BufferedOutputStream(new FileOutputStream(targetFile));
                int i;//记录获取长度
//                long j = 0;
                byte[] bt = new byte[100 * 1024];//缓冲区
                DecimalFormat df = new DecimalFormat("0.00");
                while ((i = bis.read(bt)) != -1) {
//                    j++;
                    fileExportedSize = fileExportedSize + i;
                    progress = fileExportedSize / totalSize;
                    String n = df.format(progress * 100) + "%";
                    //向单例哈希表写入进度
                    //这里需要判断断点续传进度减少的问题
                   /* String fileProgress = ProgressService.get((frontDiskBean.getId()));
                    if (StringUtils.isEmpty(fileProgress)) {
                        long fileProgressParse = Long.parseLong(fileProgress.replace("%", ""));
                        if (progress > fileProgressParse) {
                            ProgressService.put(frontDiskBean.getId(), n);
                        }
                    }*/
                    ProgressService.put(frontDiskBean.getId(), n);
                    bos.write(bt, 0, i);
                }
                //传完一个文件记录一次文件大小，不然下次续传进度就不对了
                filePositionSize += sourceFile.length();
            }
            //考完把数据写入到数据库
            if (Constant.USE_DB) {
                exportService.writeExportLogtoDb(diskBean.getTrainInfo(), String.valueOf(totalSize));
            }

            //导出完成移除断点
            Constant.positionMap.remove(diskBean.getTrainInfo());
            Constant.SizeMap.remove(diskBean.getTrainInfo(), filePositionSize);
        } catch (Exception e) {
            e.printStackTrace();
            //断点续传写入，断点文件名
            Constant.positionMap.put(diskBean.getTrainInfo(), position);
            Constant.SizeMap.put(diskBean.getTrainInfo(), filePositionSize);
            //把map里面的进度移除掉，不然给前台传数据，还会显示进度条
            ProgressService.remove(frontDiskBean.getId());
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取文件列表的方法
     */
    private static class FindJavaVisitor extends SimpleFileVisitor<Path> {
        private List<Path> result;
        private String fileSuffix = null;

        public FindJavaVisitor(List<Path> result) {
            this.result = result;
        }

        public FindJavaVisitor(List<Path> result, String fileSuffix) {
            this.result = result;
            this.fileSuffix = fileSuffix;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            //是否判断后缀名
            if (fileSuffix != null && !"".equals(fileSuffix)) {
                if (file.toString().endsWith(fileSuffix)) {
                    result.add(file);
                }
            } else {
                //result.add(file.getFileName());
                result.add(file);
            }
            //System.out.println(file.toUri());
            return FileVisitResult.CONTINUE;
        }
    }


    private static double getTotalSize(String sourcePath) {
        double total = 0.00;

        try {
            Path startingDir = Paths.get(sourcePath);
            List<Path> result = new LinkedList<Path>();
            //读取文件列表
            Files.walkFileTree(startingDir, new FindJavaVisitor(result));
            for (Path p : result) {
                //读取需要复制的文件
                File sourceFile = new File(p.toString());
                total += sourceFile.length();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total;
    }

    public static double getTotalSize1(File[] files) {
        double total = 0.00;
        for (File file : files) {
            //读取需要复制的文件
            total += file.length();
        }
        return total;
    }
}
