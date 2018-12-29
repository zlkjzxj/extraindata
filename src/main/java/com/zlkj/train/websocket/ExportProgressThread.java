package com.zlkj.train.websocket;

import com.alibaba.fastjson.JSON;
import com.zlkj.train.bean.SysUser;
import com.zlkj.train.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;


public class ExportProgressThread implements Runnable {
    private static ExportProgressThread single = null;
    private WebSocketSession session;
    private boolean flag;


    private ExportProgressThread() {
    }


    //共有访问方法 并双重检查
    public static ExportProgressThread getInstance() {
        if (single == null) {
            synchronized (ExportProgressThread.class) {
                if (single == null) {
                    single = new ExportProgressThread();
                }
            }
        }
        return single;
    }

    @Override
    public void run() {
        /*
         1：得到需要导出的盘
         2：得到扫描到的盘
         3：根据盘符变化处理，控制排序
         4：给对应的盘符加上进度，放入list中
         5：把这个list转为json，给页面
        */
        System.out.println("进入进度条socket");
        while (flag) {
            try {
                session.sendMessage(new TextMessage(JSON.toJSONString(ProgressService.getMap())));
                //发送完之后如果有100%的，就要移除了
                Map<String, String> map = ProgressService.getMap();
                map.forEach((key, value) -> {
                    if ("100.00%".equals(value)) {
                        map.remove(key);
                    }
                });
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

        /*
         if(扫到的盘不为空){
             if(导出的盘不为空){
                根据两个list得到正在导出盘符的变化情况，插入，拔出，不变，没有初始化，重复，以及缓存用作下一次操作的list
                if(车次信息map大小 > 0){
                    此时需要根据【车次信息位置map】控制顺序
                    1、判断新插入的盘有车次信息重复的盘，此时不能打断之前正在导出的盘，解决办法
                        在新插入的盘list中查找车次信息重复的盘，如果存在重复的盘，则将扫描到的盘list移除车次信息重复的盘
                    2、没有变化的盘，在【车次信息位置map】和【盘符位置map】中都是不变的。
                    3、先判断拔出的盘符，再判断插入的盘，这样就避免了只朝后拼接，不用前边的空位（也就是拔出的盘空出的
                     位置）的情况。经过这一步的操作就会把拔出的盘从【车次信息位置map】和【盘符位置map】中移除了。
                    4、
                    if(有盘拔出){
                        1、先从【车次信息位置map】中得到拔出的盘的位置，从【盘符位置map】中取出这个盘符，判断【盘符位置map】
                        中是否包含被移除的这个盘符，如果包含，则从【车次信息位置map】和【盘符位置map】移除
                        2、再将插入的盘放入空缺，放不下的朝后拼
                    }else if(没有盘拔出)｛
                        将插入的盘放入空缺，放不下的朝后拼
                        这里只需要判断插入的盘，没有变化的盘不用管
                    ｝
                }else if(车次信息map大小 <= 0){
                    表示是第一次插入盘，直接循环放入【车次信息位置map】和【盘符位置map】即可
                }
             }else if(导出的盘为空){
                 清除所有正在导出的盘的进度以及状态，
                 清空盘符位置map，
                 清空车次信息位置map
             }
         }else if(扫到的盘为空){
             if(导出的盘不为空){
                清除所有正在导出的盘的进度以及状态，
                清空盘符位置map，
                清空车次信息位置map
             }else{
                导出的盘为空，什么都不用做。
            }
         }

         */


    public void setSession(WebSocketSession session) {
        this.session = session;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
