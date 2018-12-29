package com.zlkj.train.utils;

import com.zlkj.train.bean.DiskBean;
import com.zlkj.train.bean.DiskMapBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckEquType {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static List<DiskBean> oldDisks = new ArrayList<>();//存放循环上一次的状态

    public static List<DiskBean> checkEquType() {

        File[] files = File.listRoots();
        FileSystemView fileSystemView = FileSystemView.getFileSystemView();
        List<DiskBean> disks = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            String diskType = fileSystemView.getSystemTypeDescription(files[i]);
            String name = fileSystemView.getSystemDisplayName(files[i]);
            String regex = "\\((.*)\\)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(name);//匹配类
            while (matcher.find()) {
                if (!diskType.equals("本地磁盘")) {
                    DiskBean diskBean = new DiskBean();
                    diskBean.setDiskid(matcher.group(1));
                    diskBean.setTotalSpace(FormetFileSize(files[i].getTotalSpace()));
                    diskBean.setFreeSpace(FormetFileSize(files[i].getFreeSpace()));
                    diskBean.setTrainInfo(diskBean.getDiskid());
                    disks.add(diskBean);
                }
            }
        }
        return disks;
    }


    public static Map<String, List<DiskBean>> checkEquTypeString() throws UnsupportedEncodingException {
        List<DiskBean> disks = checkEquType();
        List<DiskBean> notInitList = new ArrayList<>();//未初始化的盘
        List<DiskBean> initList = new ArrayList<>();//初始化过的盘
        String trainInfoStr;
        if (disks != null && disks.size() > 0) {
            for (DiskBean diskBean : disks) {
                String trainInfo = new ReadWriteProperties().getProperty(Constant.TRAIN_INFO, diskBean.getDiskid().concat(Constant.WRITE_INITNAME));

                //把未初始化的添加到未初始化集合 并且从原集合删除
                if ("".equals(trainInfo)) {
                    notInitList.add(diskBean);
                } else {
                    trainInfoStr = new String(trainInfo.getBytes("ISO-8859-1"), "UTF-8");
                    diskBean.setTrainInfo(trainInfoStr);
                    initList.add(diskBean);
                }
            }
        }

        Map<String, List<DiskBean>> map = getDiskChange(initList);
        map.put("notInit", notInitList);
        return map;
    }

    private static Map<String, List<DiskBean>> getDiskChange(List<DiskBean> newDisks) {
        List<DiskBean> increaseList = new ArrayList<>();
        List<DiskBean> reduceList = new ArrayList<>();
        Map<String, List<DiskBean>> map = new HashMap<>(2);
        //获取旧map的所有值，循环新map，如果旧的不包含，那么就是新增的

        newDisks.forEach((element) -> {
            if (!oldDisks.contains(element)) {//为啥能比较，是因为我重写equals 方法了
                increaseList.add(element);
            }
        });
        //获取新map的所有值，循环新旧的，如果新的不包含，那么就是减少的
        oldDisks.forEach((element) -> {
            if (!newDisks.contains(element)) {
                reduceList.add(element);
            }
        });
        map.put("increaseList", increaseList);
        map.put("reduceList", reduceList);
        oldDisks.clear();
        oldDisks.addAll(newDisks);
        return map;
    }


    /**
     * 转long为GB
     *
     * @param fileS
     * @return
     */
    public static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString;
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    public static void main(String[] args) {
//        List<String> disks = checkEquType();
//        for (int i = 0; i < disks.size(); i++) {
////            System.out.println(disks.get(i));
//        }
    }

}
