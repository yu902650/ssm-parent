package com.xiangxue.ch2.tools.semaphore;

import com.xiangxue.tools.SleepTools;

import java.sql.Connection;
import java.util.Random;

/**
 * @author bo bo
 * @date 2019/6/22 10:51
 * @desc
 */
public class DBPoolTest2 {

    private static DBPoolSemaphore dbPool = new DBPoolSemaphore();

    private static class BusiThread extends Thread{
        @Override
        public void run() {
            //让每个线程持有的连接时间不一样
            Random r =new Random();
            long start = System.currentTimeMillis();

            try {

                Connection connect = dbPool.takeConnect();
                System.out.println("Thread_" +Thread.currentThread().getId()
                + "_获取数据库链接耗时 : " + (System.currentTimeMillis()-start) + " ms.");
                //模拟业务操作,线程持有连接查询数据
                SleepTools.ms(100 + r.nextInt(100));
                System.out.println("查询数据完成,归还连接");
                dbPool.returnConnect(connect);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0 ; i < 50 ;i++){
            Thread thread = new BusiThread();
            thread.start();
        }
    }

}
