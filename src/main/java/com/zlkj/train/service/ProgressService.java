package com.zlkj.train.service;


import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: zxj
 * @Date: 2018\12\14 0014 10:25
 * @Description:
 */
public class ProgressService {

    //为了防止多用户并发，使用线程安全的ConcurrentHashMap 他比hashtable 速度快
    private static Map<String, String> map = new ConcurrentHashMap<>();

    public static void put(String key, String value) {
        map.put(key, value);
    }

    public static String get(String key) {
        return map.get(key);
    }

    public static Map<String, String> getMap() {
        return map;
    }

    public static void setMap(Hashtable<String, String> map) {
        ProgressService.map = map;
    }

    public static void remove(String key) {
        map.remove(key);
    }
}
