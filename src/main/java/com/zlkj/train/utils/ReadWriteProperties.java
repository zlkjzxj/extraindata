package com.zlkj.train.utils;

import java.io.*;
import java.util.Properties;

public class ReadWriteProperties {
    private static Properties properties = new Properties();
    FileInputStream fis = null; // 读
    OutputStream fos;

    /**
     * 根据键读取配置文件
     *
     * @param key
     * @return
     */
    public String getProperty(String key, String filePath) {
        String str = "";
        File file = new File(filePath);
        if (file.exists()) {
            try {
                fis = new FileInputStream(filePath);
                properties.load(fis);
                Object object = properties.get(key);
                if (object != null) {
                    str = object.toString();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    /**
     * 设置配置文件，根据路径和键设置值
     *
     * @param key
     * @param value
     * @param path
     */
    public void setProperty(String key, String value, String path) throws Exception {
        fos = new FileOutputStream(path);// 加载读取文件流
        properties.setProperty(key, value);
        properties.store(new OutputStreamWriter(fos, "utf-8"), "write init file");
        fos.close();
    }

}
