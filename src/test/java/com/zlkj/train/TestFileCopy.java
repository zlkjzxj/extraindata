package com.zlkj.train;

import java.io.File;
import java.util.Date;

/**
 * @Auther: zxj
 * @Date: 2018\12\5 0005 17:02
 * @Description: 复制文件
 * n个线程
 */
public class TestFileCopy {
    public static void main(String[] args) {
        File src = new File("G:\\was6.1.rar");
        File tar = new File("F:\\was6.1.rar");
        int n = 5;
        Date startDate = new Date();
        // 分几部分复制
        for (int i = 0; i < n; i++) {// 每一部分的编号
            new Thread(new FileCopy(src, tar, n, i)).start();
        }
        System.out.println("复制文件用" + n + "线程，用时：" + (new Date().getTime() - startDate.getTime()));
    }
}