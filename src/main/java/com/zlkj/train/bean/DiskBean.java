package com.zlkj.train.bean;

public class DiskBean {
    private String diskid;//盘符
    private boolean hasini;//是否有初始化文件
    private String trainInfo;
    private String totalSpace;
    private String freeSpace;
    private String progress;

    public String getDiskid() {
        return diskid;
    }

    public void setDiskid(String diskid) {
        this.diskid = diskid;
    }

    public boolean isHasini() {
        return hasini;
    }

    public void setHasini(boolean hasini) {
        this.hasini = hasini;
    }

    public String getTrainInfo() {
        return trainInfo;
    }

    public void setTrainInfo(String trainInfo) {
        this.trainInfo = trainInfo;
    }

    public String getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(String totalSpace) {
        this.totalSpace = totalSpace;
    }

    public String getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(String freeSpace) {
        this.freeSpace = freeSpace;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getTrainInfo().equals(((DiskBean) obj).getTrainInfo());
    }
}
