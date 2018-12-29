package com.zlkj.train;

/**
 * @Auther: zxj
 * @Date: 2018\12\18 0018 10:10
 * @Description:
 */
public class Father {
    public static int aa = 10;
    public int bb = 100;

    {
        speak("构造块");
    }

    static {
        speak("静态块");
    }

    public Father() {
        speak("构造方法");
    }

    public static void speak(String str) {
        System.out.println(str);
    }
    public static void eat() {
        System.out.println("我是eat 方法，为啥没人吊我");
    }
}
