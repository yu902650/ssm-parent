package com.xiangxue.ch3;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author bo bo
 * @date 2019/6/25 17:32
 * @desc
 */
public class UserAtomicArray {

    static int[] value = new int[]{1,2};

    static AtomicIntegerArray ai = new AtomicIntegerArray(value);

    public static void main(String[] args) {
//        getAndSet(当前数组的下标,新的值)
        ai.getAndSet(0,3);
        System.out.println(ai.get(0));
        System.out.println(value[0]);

    }


}
