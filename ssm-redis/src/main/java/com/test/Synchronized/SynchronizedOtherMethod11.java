package com.test.Synchronized;

/**
 * @Project_name: parent
 * @Package_name: com.test.Synchronized
 * @author: bobo_yu
 * @create_time: 2018/12/22 14:05
 * @description:
 *
 *      可重入粒度测试：调用类内另外的方法
 */
public class SynchronizedOtherMethod11 {

    public synchronized void method1(){
        System.out.println("我的method1");
        method2();

    }

    private synchronized void method2() {
        System.out.println("我的method2");

    }
    public static void main(String[] args){
        SynchronizedOtherMethod11 s = new SynchronizedOtherMethod11();
        s.method1();
    }

}
