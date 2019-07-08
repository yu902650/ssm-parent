package com.test.Synchronized;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Project_name: parent
 * @Package_name: com.test.Synchronized
 * @author: bobo_yu
 * @create_time: 2018/12/22 14:24
 * @description:
 */
public class SynchronizedToLock13 {
    Lock lock = new ReentrantLock();

    public synchronized void method1(){
        System.out.println("我是synchronized形式的锁");
    }

    public void method2(){
        lock.lock();
        try {
            System.out.println("我是lock形式的锁");
        }finally {
            //释放锁
            lock.unlock();
        }
    }

    public static void main(String[] args){
        SynchronizedToLock13 s = new SynchronizedToLock13();
        s.method1();
        s.method2();
    }

}
