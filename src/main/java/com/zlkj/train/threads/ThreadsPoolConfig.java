package com.zlkj.train.threads;

import org.springframework.stereotype.Component;

/**
 * 线程池 配置
 */
@Component
public class ThreadsPoolConfig {

    public ThreadsPoolConfig() {
    }

    public ThreadsPoolConfig(boolean custom, int corePoolSize, int maximumPoolSize, long keepAliveTime, int workQueue) {
        this.custom = custom;
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
        this.workQueue = workQueue;
    }

    /**
     * 是否开启自定义线程池
     */
    private boolean custom;
    /**
     * 核心线程数
     */
    private int corePoolSize;
    /**
     * 线程池最大线程数
     */
    private int maximumPoolSize;
    /**
     * 空闲线程存活时间（对核心线程无效）
     */
    private long keepAliveTime;
    /**
     * 任务队列大小，0时为无界队列
     */
    private int workQueue;

    public boolean isCustom() {
        return custom;
    }

    public void setCustom(boolean custom) {
        this.custom = custom;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public int getWorkQueue() {
        return workQueue;
    }

    public void setWorkQueue(int workQueue) {
        this.workQueue = workQueue;
    }

}
