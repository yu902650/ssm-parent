package com.xiangxue.ch1.pool;

import javafx.concurrent.Worker;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author bo bo
 * @date 2019/6/19 10:57
 * @desc
 */
public class DBPoolTest {

    //TODO 并发工具类之后,再回来看.
    static DBPool pool = new DBPool(10);

    //控制器 : 控制main线程将会等待所有Worker结束后才能继续执行
    static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 50;
        end = new CountDownLatch(threadCount);

        int count = 20; //每个线程的操作次数
        AtomicInteger got = new AtomicInteger(); //计数器 ; 统计可以拿到的连接线程
        AtomicInteger notGot = new AtomicInteger(); //计数器 : 统计没有拿到连接的线程
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new Worker(count, got, notGot), "worker_" + i);
            thread.start();
        }
        end.await(); //main 线程在此处等待
        System.out.println("总共尝试了:" + (threadCount * count));
        System.out.println("拿到连接的次数" + got);
        System.out.println("没能连接的次数:" + notGot);
    }

    static class Worker implements Runnable {
        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public Worker(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            while (count > 0) {
                try {
                    // 从线程池中获取连接,如果1000ms内无法获取,将会返回null
                    // 分别统计连接获取的数量 got和未获取的数量 notGot
                    Connection connection = pool.fetchConn(1000);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConn(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.incrementAndGet();
                        System.out.println(Thread.currentThread().getName() + "等待超时");
                    }

                } catch (Exception e) {

                }finally {
                    count--;
                }
            }
            end.countDown();
        }
    }

}
