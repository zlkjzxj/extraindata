package com.zlkj.train;

import java.util.Collections;
import java.util.Comparator;

/**
 * @Auther: zxj
 * @Date: 2018\12\20 0020 14:56
 * @Description:
 */
@FunctionalInterface
public interface FuncitionalTest<T> {
    int compare(T o1,T o2);
    boolean equals(Object obj);
    default FuncitionalTest<T> reversed() {
        return null;
    }
}
