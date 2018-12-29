package com.zlkj.train.bean;

import java.util.Hashtable;

/**
 * @Auther: zxj
 * @Date: 2018\12\21 0021 15:35
 * @Description:
 */
public class ProgressResult {
    private Hashtable<String, String> table;
    private SysUser user;

    public Hashtable<String, String> getTable() {
        return table;
    }

    public void setTable(Hashtable<String, String> table) {
        this.table = table;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }
}

