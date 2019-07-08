package com.random;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by bobo on 2019/4/21 20:55
 * <p>
 * random  会重复:
 * <p>
 * 通过Random生成的随机数，并不是真正的随机，
 * 它有一个种子的概念，是根据种子值来计算【下一个】值的，
 * 如果种子值相同，那么它生成出来的随机数也必定相等，也就是“确定的输入产生确定的输出”。
 */
public class RandomTest {

    /**
     * 正常
     *
     * @param args
     */
//    public static void main(String[] args) {
//        Random random = new Random();
//        System.out.println(random.nextInt(100));
//    }

    /**
     * 重复
     * @param args
     */
//    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            Random random = new Random(15);
//            System.out.println(random.nextInt(100));
//        }
//    }

    /**
     * 解决重复的办法
     *
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
            System.out.println(threadLocalRandom.nextInt(100));
        }
    }


}
