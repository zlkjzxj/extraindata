package com.zlkj.train.utils;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FileOrder {
    /**
     * 断点续传
     * 第一种是先给文件按时间排序，然后在记录断点的那个文件
     * 下次从这个文件开始重新传，这样的话进度会有一点问题，例如上次跑到58 了，这次又从50开始
     *
     * 第二种，开始改进
     * 确定断点的文件，并确定断点的位置，然后继续传下面的文件
     *
     */


    //按日期排序

    /**
     * 传进来文件的所有列表和断点，判断断点是否为空，不是空的话就把文件列表改成从文件名开始之后的所有文件
     *
     * @param result
     * @param position
     * @return
     */
    public static File[] orderByDate(List<Path> result, File position) {
        File[] fs = new File[result.size()];
        for (int i = 0; i < result.size(); i++) {
            File file = new File(result.get(i).toString());
            fs[i] = file;
        }
        Arrays.sort(fs, new Comparator<File>() {
            public int compare(File f1, File f2) {
                long diff = f1.lastModified() - f2.lastModified();
                if (diff > 0)
                    return 1;
                else if (diff == 0)
                    return 0;
                else
                    return -1;
            }

            public boolean equals(Object obj) {
                return true;
            }

        });
        //先排序
        //如果有断点，就添加断点后面的
        int kk = position != null ? getPosition(fs, position) : -1;
        if (kk != -1) {
            File[] _fs = Arrays.copyOfRange(fs, kk, fs.length);
            return _fs;
        }
        return fs;
    }

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] newData;
        newData = Arrays.copyOfRange(data, 2, 9);
        for (int i : newData)
            System.out.print(i + " ");
    }

    public static int getPosition(File[] fs, File position) {
        int j = -1;
        for (int i = 0; i < fs.length; i++) {
            System.out.println(fs[i].getName() + "|||" + position.getName());
            if (fs[i].getName().equals(position.getName())) {
                j = i;
                break;
            }
        }
        return j;
    }
}
