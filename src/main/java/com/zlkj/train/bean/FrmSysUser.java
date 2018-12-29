package com.zlkj.train.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FrmSysUser {
    private String yhdh;//用户代号
    private String yhgh;//警员编号
    private String sfzmhm;//身份证明号码
    private String xm;//姓名
    @JsonIgnore
    private String mm;//密码
    private String yhyxq;//用户有效期
    private String mmyxq;//密码有效期
    private String glbm;//管理部门
    private String yhjb;//用户级别
    private String yhlb;//用户类别
    private String qxms;//权限组
    private String ipks;
    private String ipjs;
    private String gdip1;
    private String gdip2;
    private String mac;
    private String zt;//状态
    private String sfgly;
    private String dlms;
    private String zhdlsj;
    private String bz;//备注
    private String lxdh;
    private String bhxj;//是否包含下级
    private String cxdh;

    public String getYhdh() {
        return yhdh;
    }

    public void setYhdh(String yhdh) {
        this.yhdh = yhdh;
    }

    public String getYhgh() {
        return yhgh;
    }

    public void setYhgh(String yhgh) {
        this.yhgh = yhgh;
    }

    public String getSfzmhm() {
        return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
        this.sfzmhm = sfzmhm;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }

    public String getYhyxq() {
        return yhyxq;
    }

    public void setYhyxq(String yhyxq) {
        this.yhyxq = yhyxq;
    }

    public String getMmyxq() {
        return mmyxq;
    }

    public void setMmyxq(String mmyxq) {
        this.mmyxq = mmyxq;
    }

    public String getGlbm() {
        return glbm;
    }

    public void setGlbm(String glbm) {
        this.glbm = glbm;
    }

    public String getYhjb() {
        return yhjb;
    }

    public void setYhjb(String yhjb) {
        this.yhjb = yhjb;
    }

    public String getYhlb() {
        return yhlb;
    }

    public void setYhlb(String yhlb) {
        this.yhlb = yhlb;
    }

    public String getQxms() {
        return qxms;
    }

    public void setQxms(String qxms) {
        this.qxms = qxms;
    }

    public String getIpks() {
        return ipks;
    }

    public void setIpks(String ipks) {
        this.ipks = ipks;
    }

    public String getIpjs() {
        return ipjs;
    }

    public void setIpjs(String ipjs) {
        this.ipjs = ipjs;
    }

    public String getGdip1() {
        return gdip1;
    }

    public void setGdip1(String gdip1) {
        this.gdip1 = gdip1;
    }

    public String getGdip2() {
        return gdip2;
    }

    public void setGdip2(String gdip2) {
        this.gdip2 = gdip2;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getSfgly() {
        return sfgly;
    }

    public void setSfgly(String sfgly) {
        this.sfgly = sfgly;
    }

    public String getDlms() {
        return dlms;
    }

    public void setDlms(String dlms) {
        this.dlms = dlms;
    }

    public String getZhdlsj() {
        return zhdlsj;
    }

    public void setZhdlsj(String zhdlsj) {
        this.zhdlsj = zhdlsj;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getBhxj() {
        return bhxj;
    }

    public void setBhxj(String bhxj) {
        this.bhxj = bhxj;
    }

    public String getCxdh() {
        return cxdh;
    }

    public void setCxdh(String cxdh) {
        this.cxdh = cxdh;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }
}
