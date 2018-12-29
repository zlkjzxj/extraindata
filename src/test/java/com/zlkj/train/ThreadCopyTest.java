package com.zlkj.train;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @Auther: zxj
 * @Date: 2018\12\5 0005 16:24
 * @Description:
 */
public class ThreadCopyTest {
    public static void main(String[] args) throws Exception {
        File file = new File("G:\\was6.1.rar");
        startThread(4, file.length(), "G:\\was6.1.rar",
                "F:\\was6.1.rar");
    }

    /**
     * 开启多线程复制
     *
     * @param threadnum      线程数
     * @param fileLength     文件大小（用于确认每个线程下载多少东西）
     * @param sourseFilePath 源文件目录
     * @param targerFilePath 目标文件目录
     */
    public static void startThread(int threadnum, long fileLength, String sourseFilePath, String targerFilePath) {
        Instant inst1 = Instant.now();
        System.out.println("Inst1 : " + inst1);
        System.out.println("================");
        System.out.println(fileLength);
        long modLength = fileLength % threadnum;
        System.out.println("modLength:" + modLength);
        long targetLength = fileLength / threadnum;
        System.out.println("targetLength:" + targetLength);
        for (int i = 0; i < threadnum; i++) {
            System.out.println((targetLength * i) + "-----" + (targetLength * (i + 1)));
            new FileWriteThread((targetLength * i), (targetLength * (i + 1)), sourseFilePath, targerFilePath).start();
        }
        if (modLength != 0) {
            System.out.println("最后的文件写入");
            System.out.println((targetLength * threadnum) + "-----" + (targetLength * threadnum + modLength));
            new FileWriteThread((targetLength * threadnum), targetLength * threadnum + modLength + 1, sourseFilePath,
                    targerFilePath).start();
        }
//        new FileWriteThread(0, fileLength, sourseFilePath, targerFilePath).start();
        Instant inst2 = Instant.now();
        System.out.println("Inst2 : " + inst2);
        System.out.println("Difference in seconds : " + Duration.between(inst1, inst2).getSeconds());
    }

    /**
     * 写线程：指定文件开始位置、目标位置、源文件、目标文件，
     */
    static class FileWriteThread extends Thread {
        private long begin;
        private long end;
        private RandomAccessFile soursefile;
        private RandomAccessFile targerFile;

        public FileWriteThread(long begin, long end, String sourseFilePath, String targerFilePath) {
            this.begin = begin;
            this.end = end;
            try {
                this.soursefile = new RandomAccessFile(sourseFilePath, "rw");
                this.targerFile = new RandomAccessFile(targerFilePath, "rw");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                soursefile.seek(begin);
                targerFile.seek(begin);
                int hasRead = 0;
                byte[] buffer = new byte[1];
                while (begin < end && -1 != (hasRead = soursefile.read(buffer))) {
                    // System.out.println("hasRead:"+hasRead);
                    begin += hasRead;
                    targerFile.write(buffer, 0, hasRead);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    soursefile.close();
                    targerFile.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
