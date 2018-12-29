package com.zlkj.train;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018\12\4 0004 10:00
 * @Description:
 */
public class Test {
    static List<Integer> list1 = new ArrayList<>();

    public static void main(String[] args) {
       /* for (int i = 0; i < 10; i++) {
            if (i == 3) {
                System.out.println(i);
                continue;
            }
            if (i == 6) {
                System.out.println(i);
                break;
            }
            if (i == 7) {
                System.out.println(i);
            }
            System.out.println("fuck" + i);
        }
        for (int i = 0; i < 5; i++) {
            if (i != 1 && i != 2 && i != 3) {
                System.out.println(i);
            }
        }
        System.out.println(!true && !false && !false);
        System.out.println("循环完了");*/

        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list1.addAll(list2);
        list2 = new ArrayList<>();
        list2.add(4);
        list2.add(5);
        list1.clear();
        list1.addAll(list2);
    }
}
