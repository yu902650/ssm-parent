package com.equals;

import java.util.Arrays;
import java.util.List;

/**
 * Created by bobo on 2019/5/3 23:18
 *
 *
 * 看源码，我为什么推荐IDEA?
 *
 * 1.条件断点
 *
 * 看源码的时候,经常遇到这个情况,源码中有个for循环,关键是这个list的size有时候长达数百个.
 * 但是我们只想debug一种情况.肥朝就曾经见过,在for循环中打了断点,一直按跳过,按了数十下之后.
 * 才找到自己想debug的值.这样效率不高
 *
 * 比如下文这个
 * 如果你想debug数字10这种情况,
 * 如果你不知道条件断点,
 * 那么你可能要一直点9次跳过.
 * 我们来看一下条件断点的使用
 *
 * 断点打在  System.out.println(integer); 右击断点,Condition输入 integer.equals(10);
 * 释放断点
 *
 */
public class DebugTest1 {


    public void testList() throws Exception {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    public static void main(String[] args) throws Exception {
        DebugTest1 test = new DebugTest1();
        test.testList();
    }

}
