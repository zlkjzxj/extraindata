package com.zlkj.train;

/**
 * @Auther: zxj
 * @Date: 2018\12\18 0018 10:11
 * @Description:
 */
public class Son extends Father {

    {
        speak("子类构造块");
    }

    static {
        speak("子类静态块");
    }

    public Son() {
        speak("子类构造方法1");
    }

    public Son(String name) {
        speak("子类构造方法" + name);
    }

    public static void speak(String str) {
        System.out.println(str);
    }

    public static void main(String[] args) {
        Father.eat();
    }
}
