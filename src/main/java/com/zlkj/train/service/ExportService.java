package com.zlkj.train.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zlkj.train.bean.DiskBean;
import com.zlkj.train.bean.DiskMapBean;
import com.zlkj.train.bean.FrontDiskBean;
import com.zlkj.train.threads.ExportHandler;
import com.zlkj.train.threads.HandlerThreadsPool;
import com.zlkj.train.utils.CheckEquType;
import com.zlkj.train.utils.Constant;
import com.zlkj.train.utils.HttpClientUtil;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Auther: zxj
 * @Date: 2018\12\10 0010 15:10
 * @Description:
 */
@Service
public class ExportService {
    private HandlerThreadsPool handlerThreadsPool;

    private final StartThreadPoolService startThreadPoolService;


    public ExportService(StartThreadPoolService startThreadPoolService) {
        this.startThreadPoolService = startThreadPoolService;
        handlerThreadsPool = startThreadPoolService.createThreadPool();
    }

    public void exportData(FrontDiskBean diskBean) {
        handlerThreadsPool.execute(new ExportHandler(diskBean));
    }

    public void writeInitLogtoDb(String trainInfo, String glbm, String yhdh) throws UnknownHostException {
        Map<String, String> map = new HashMap<>();
        String address = InetAddress.getLocalHost().getHostAddress();
        map.put("sbip", address);
        map.put("ccxx", trainInfo);
        map.put("cshbm", glbm);
        map.put("cshcs", "0");
        map.put("cshry", yhdh);
        map.put("sblx", "0");
        map.put("sbzt", "0");
        HttpClientUtil.doPost(Constant.HOST.concat(Constant.INIT_LOG_URL), map, "utf-8");
    }

    public void writeExportLogtoDb(String trainInfo, String totalSize) throws UnknownHostException {
        Map<String, String> map = new HashMap<>();
        String address = InetAddress.getLocalHost().getHostAddress();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        map.put("dckssj", now.format(formatter));
        map.put("ccxx", trainInfo);
        map.put("sbip", address);
        map.put("wjdx", totalSize);
        HttpClientUtil.doPost(Constant.HOST.concat(Constant.EXPORT_LOG_URL), map, "utf-8");
    }
}
