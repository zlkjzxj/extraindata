package com.zlkj.train.service;

import com.zlkj.train.threads.HandlerThreadsPool;
import com.zlkj.train.threads.ThreadsPoolConfig;
import org.springframework.stereotype.Service;

/**
 * @Auther: zxj
 * @Date: 2018\12\13 0013 17:12
 * @Description:
 */
@Service
public class StartThreadPoolService {
    //开启线程池
    public HandlerThreadsPool createThreadPool() {
        ThreadsPoolConfig config = new ThreadsPoolConfig();
        config.setCustom(true);
        config.setCorePoolSize(20);
        config.setMaximumPoolSize(25);
        // 初始化 线程池
        return new HandlerThreadsPool(config);
    }
}
