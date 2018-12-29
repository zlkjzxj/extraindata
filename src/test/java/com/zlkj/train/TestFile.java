package com.zlkj.train;

import java.io.File;

/**
 * @Auther: zxj
 * @Date: 2018\12\24 0024 11:31
 * @Description:
 */
public class TestFile {
    static long sum = 0;

    public static void main(String[] args) {
        File dir = new File("E:\\ideaworkspace\\boy");
        System.out.println("总和：" + getAllFiles(dir));
    }

    //计算出总和，断点续传，文件名和剩余的文件
    public static long getAllFiles(File dir)
    {
        //System.out.println("文件"+dir.getName()+"的大小是："+dir.length());
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    //这里面用了递归的算法
                    getAllFiles(file);
                } else {
                    sum += file.length();
                    System.out.println(file.length());
                    System.out.println(file + "的大小：" + file.length());
                }
            }
        }
        return sum;
    }
}
