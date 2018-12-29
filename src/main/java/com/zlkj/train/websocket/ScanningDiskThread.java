package com.zlkj.train.websocket;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zlkj.train.bean.DiskBean;
import com.zlkj.train.bean.DiskInfo;
import com.zlkj.train.utils.CheckEquType;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zxj
 * @Date: 2018\12\5 0005 14:14
 * @Description:
 */
public class ScanningDiskThread implements Runnable {

    private static ScanningDiskThread single = null;
    private WebSocketSession session;
    private boolean flag;
    private List<DiskInfo> oldDiskList = new ArrayList<>();

    public WebSocketSession getSession() {
        return session;
    }

    public void setSession(WebSocketSession session) {
        this.session = session;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    //私有构造
    private ScanningDiskThread() {
    }

    //共有访问方法 并双重检查
    public static ScanningDiskThread getInstance() {
        if (single == null) {
            synchronized (ScanningDiskThread.class) {
                if (single == null) {
                    single = new ScanningDiskThread();
                }
            }
        }
        return single;
    }


    @Override
    public void run() {
        while (flag) {
            Map<String, List<DiskBean>> map = null;
            try {
                map = CheckEquType.checkEquTypeString();
                synchronized (session) {
                    //为了防止引用
                    String disksInfo = JSONObject.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
                    session.sendMessage(new TextMessage(disksInfo));
                }
                Thread.sleep(10000);

            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

}
