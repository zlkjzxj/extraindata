package com.zlkj.train.threads;


import javax.annotation.PreDestroy;
import java.util.concurrent.*;

/**
 * 线程管理器
 */
public class HandlerThreadsPool {


    private ExecutorService executorService;

    public HandlerThreadsPool(ThreadsPoolConfig config) {
        if (config.isCustom()) {
            BlockingQueue<Runnable> queue;
            if (config.getWorkQueue() > 0) {
                queue = new LinkedBlockingQueue<>(config.getWorkQueue()); // 一般使用 LinkedBlockingQueue 队列
            } else {
                queue = new LinkedBlockingQueue<>();
            }
            // 配置线程池信息
            this.executorService = new ThreadPoolExecutor(
                    config.getCorePoolSize(),
                    config.getMaximumPoolSize(),
                    config.getKeepAliveTime(),
                    TimeUnit.SECONDS,
                    queue,
                    new ThreadPoolExecutor.AbortPolicy()//拒绝策略，任务队列满后，新的任务将被丢弃，并抛出异常
            );
        } else {
            this.executorService = Executors.newCachedThreadPool();
        }
    }

    /*
     * 创建线程，对线程处理事件
     */
    public void execute(Runnable runnable) {
        executorService.execute(runnable);
    }


    /*
     * 对象销毁时，销毁线程
     */

    @PreDestroy
    public void stop() {
        executorService.shutdown();
    }

}