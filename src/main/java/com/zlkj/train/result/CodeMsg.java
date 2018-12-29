package com.zlkj.train.result;

public class CodeMsg {
    private String code;
    private String msg;

    // 通用异常
    public static CodeMsg SUCCESS = new CodeMsg("0", "success");
    //http访问
    public static CodeMsg HTTP_SUCCESS = new CodeMsg("200", "接口访问成功");

    //项目启动模块
    public static CodeMsg JSON_FIlE_NOT_EXIST = new CodeMsg("10001", "配置文件读取失败");
    public static CodeMsg VISIT_MANAGER_ERR = new CodeMsg("10002", "管理端访问失败");
    public static CodeMsg GET_PARAM_ERR = new CodeMsg("10002", "访问参数列表失败");
    //登录模块
    public static CodeMsg USERNAME_PASSOERD_ERR = new CodeMsg("20001", "用户名或密码错误");

    private CodeMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object... args) {
        String code = this.code;
        String msg = String.format(this.msg, args);
        return new CodeMsg(code, msg);
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "{\"code\":\"" + code + "\",\"msg\":\"" + msg + "\"}";

    }
}
