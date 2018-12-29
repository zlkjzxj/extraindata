package com.zlkj.train.websocket;

import com.zlkj.train.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @Auther: zxj
 * @Date: 2018\12\5 0005 13:59
 * @Description:
 */
public class InitMessageHandler extends TextWebSocketHandler {
    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
//        session.sendMessage(new TextMessage("server connect success!"));//连接成功，发送消息，不发了没啥意义。
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        System.out.println("message:" + message.getPayload());
//        if (!Constant.IS_CONNECT) {
        //为了防止多个浏览器访问或者刷新，创建一个全局变量来控制，如果有一个来访问了，设置他为true
        Constant.IS_CONNECT = true;
        //调用扫盘方
        Thread thread = new Thread(ScanningDiskThread.getInstance());
        //System.out.println("sessionid:" + session.getId());
        ScanningDiskThread.getInstance().setSession(session);
        boolean flag = ScanningDiskThread.getInstance().isFlag();
        if(!flag){
            ScanningDiskThread.getInstance().setFlag(true);
        }
        thread.start();
//        } else {
//            logger.error("已经有一个人连接了，你还连接各毛线！");
//        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        ScanningDiskThread.getInstance().setFlag(false);
    }
}
