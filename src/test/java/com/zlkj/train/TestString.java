package com.zlkj.train;

/**
 * @Auther: zxj
 * @Date: 2018\12\21 0021 16:32
 * @Description:
 */
public class TestString {
    public static void main(String[] args) {
        b bb = new b();
        for (int i = 0; i < 10000; i++) {
            new Thread(bb::test).start();

        }
    }
}

class b {
    String a = "";
    StringBuilder b = new StringBuilder();
    StringBuffer c = new StringBuffer();

    public void test() {
        a += "1";
        b.append("1");
        c.append("1");
        System.out.println(a.length() + "|" + b.length() + "|" + c.length());
    }
}