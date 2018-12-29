package com.zlkj.train.bean;

import java.util.Map;

public class DiskMapBean {
    private Map<Integer, String> add;
    private Map<Integer, String> rm;
    private Map<Integer, DiskBean> addInfo;


    public DiskMapBean(Map<Integer, String> add, Map<Integer, String> rm, Map<Integer, DiskBean> addInfo) {
        this.add = add;
        this.rm = rm;
        this.addInfo = addInfo;
    }

    public Map<Integer, String> getAdd() {
        return add;
    }

    public void setAdd(Map<Integer, String> add) {
        this.add = add;
    }

    public Map<Integer, String> getRm() {
        return rm;
    }

    public void setRm(Map<Integer, String> rm) {
        this.rm = rm;
    }

    public Map<Integer, DiskBean> getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(Map<Integer, DiskBean> addInfo) {
        this.addInfo = addInfo;
    }
}
