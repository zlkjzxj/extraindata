package com.zlkj.train.utils;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Constant {

    /*配置文件地址*/
    public static final String FILE_PATH = "D:\\train\\properties\\properties.json";

    /*测试后台是否启动的Url*/
    public static final String TEST_DB_URL = "/train/csjk.do?method=selectPublicCs&gjz=HOST";

    /*测试后台是否启动的Url*/
    public static final String GET_PARAMS_URL = "/train/csjk.do?method=selectPublicCsList";

    /*登录的Url*/
    public static final String LOGIN_URL = "/train/loginjk.do?method=trainLoginJk";

    public static final String TRAIN_INFO = "trainInfo";//初始化车次信息键
    public static final String WRITE_INITNAME = "init.ini";//初始化文件名

    public static String YHJB = "1";//登录用户的用户级别

    public static boolean IS_CONNECT = false;
    public static String WRITEFILETO = "";//初始化车次信息键F:\test\


    public static Map<String, File> positionMap = new ConcurrentHashMap<>();//用于存放断点续传的断点
    public static Map<String, Double> SizeMap = new ConcurrentHashMap<>();//用于存放断点剩余容量

    /*是否使用数据库*/
    public static boolean USE_DB = false;

    /* 全局错误，项目启动报错，写到里面，页面加载完成，提示这个错误*/
    public static String GLOBAL_MESSAGE = "";

    /**
     * 全局变量
     */

    /**
     * 保存数据接口地址
     */
    public static String INIT_LOG_URL = "/train/initDevice.do?method=saveInitDevice";//导出日志保存地址
    public static String EXPORT_LOG_URL = "/train/outDataLog.do?method=saveOutDataLog";//导出日志保存地址

    /*后台主机地址*/
    public static String HOST = "";
    /*拷贝文件后缀*/
    public static String FILE_SUFFIX = "";
    /*文件拷贝完是否删除*/
    public static String IS_DELETE = "";
}