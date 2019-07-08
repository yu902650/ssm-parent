package com.xiangxue.ch3;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author bo bo
 * @date 2019/6/25 16:18
 * @desc
 */
public class UserAtomicInt {


    static AtomicInteger ai = new AtomicInteger(10);

    public static void main(String[] args) {
        System.out.println(ai.getAndIncrement());  //10------>11
        System.out.println(ai.incrementAndGet());  //11------>12------>out
        System.out.println(ai.get());
    }
}
