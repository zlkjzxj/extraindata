package com.zlkj.train.websocket;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zlkj.train.bean.DiskBean;
import com.zlkj.train.bean.FrontDiskBean;
import com.zlkj.train.service.ExportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;

/**
 * @Auther: zxj
 * @Date: 2018\12\5 0005 13:59
 * @Description:
 */
public class ExportProgressHandler extends TextWebSocketHandler {
    @Autowired
    private ExportService exportService;
    Logger logger = LoggerFactory.getLogger(this.getClass().getName());


    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        String msg = message.getPayload();

        FrontDiskBean diskBean = JSONObject.parseObject(msg, FrontDiskBean.class);

        //启动导出数据线程
        exportService.exportData(diskBean);
        //启动传输进度条线程 ,
        Thread thread = new Thread(ExportProgressThread.getInstance());
        ExportProgressThread.getInstance().setSession(session);
        boolean flag = ExportProgressThread.getInstance().isFlag();
        if (!flag) {
            ExportProgressThread.getInstance().setFlag(true);
        }
        thread.start();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        ExportProgressThread.getInstance().setFlag(false);
    }
}
