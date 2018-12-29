package com.zlkj.train;

/**
 * @Auther: zxj
 * @Date: 2018\12\5 0005 17:03
 * @Description:
 */

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileCopy implements Runnable {
    private File src;// 源文件
    private File tar;// 目标文件
    private int n;// 分几部分
    private int no;// 每部分的编号

    public FileCopy(File src, File tar, int n, int no) {
        this.src = src;
        this.tar = tar;
        this.n = n;
        this.no = no;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            RandomAccessFile rafsrc = new RandomAccessFile(src, "r");
//            String fileName = src.getName();
//            tar = new File(tar + File.separator + fileName);
            RandomAccessFile raftar = new RandomAccessFile(tar, "rw");
            long len = src.length();
            long size = len % n == 0 ? len / n : len / n + 1;// 每部分的字节数
            byte[] b = new byte[1024 * 8];// 每次读取的文件大小
            int num = 0;// 每次读取的字节数
            long start = size * no;// 读写的起始位置
            rafsrc.seek(start);
            raftar.seek(start);
            int sum = 0;// 累加每次读取个数
            while ((num = rafsrc.read(b)) != -1 && sum < size) {
                raftar.write(b, 0, num);
                sum += num;
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}