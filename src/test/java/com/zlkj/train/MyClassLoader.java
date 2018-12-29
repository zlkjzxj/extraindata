package com.zlkj.train;

/**
 * @Auther: zxj
 * @Date: 2018\12\18 0018 11:00
 * @Description:
 */
public class MyClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }
    
}
