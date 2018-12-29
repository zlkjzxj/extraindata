package com.zlkj.train;

/**
 * @Auther: zxj
 * @Date: 2018\12\18 0018 09:20
 * @Description:
 */
public class TestStatic {

    private static int k = 0;

    public static TestStatic t1 = new TestStatic("t1");

    public static TestStatic t2 = new TestStatic("t2");
    private static int i = print("i");
    private static int n = 99;
    public int j = print("j");

    {
        print("构造块");
    }

    static {
        print("静态块");
    }

    private TestStatic(String str) {
        System.out.println((++k) + ":" + str + " i=" + i + " n=" + n);
        ++n;
        ++i;
    }


    private static int print(String str) {
        System.out.println((++k) + ":" + str + " i=" + i + " n=" + n);
        ++i;
        return ++n;
    }


    public static void main(String[] args) {
        TestStatic t = new TestStatic("init");
    }

}
