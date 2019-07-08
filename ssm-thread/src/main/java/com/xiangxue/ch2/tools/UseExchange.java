package com.xiangxue.ch2.tools;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Exchanger;

/**
 * @author bo bo
 * @date 2019/6/22 11:01
 * @desc
 */
public class UseExchange {
    private static final Exchanger<Set<String>> exchange
            = new Exchanger<Set<String>>();

    public static void main(String[] args) {

        //第一个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                Set<String> setA = new HashSet<String>();//存放数据的容器
                try {
                    /*添加数据
                     * set.add(.....)
                     * */
                    setA.add("1");
                    setA = exchange.exchange(setA);//交换set
                    /*处理交换后的数据*/
                } catch (InterruptedException e) {
                }
                System.out.println("setA " +setA);
            }
        }).start();

        //第二个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                Set<String> setB = new HashSet<String>();//存放数据的容器
                try {
                    /*添加数据
                     * set.add(.....)
                     * set.add(.....)
                     * */
                    setB.add("2");
                    setB = exchange.exchange(setB);//交换set
                    /*处理交换后的数据*/
                } catch (InterruptedException e) {
                }
                System.out.println("setB " + setB);
            }
        }).start();
    }
}
