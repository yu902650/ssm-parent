package com.test.Synchronized;

/**
 * @Project_name: parent
 * @Package_name: com.test.Synchronized
 * @author: bobo_yu
 * @create_time: 2018/12/22 14:08
 * @description:
 *          可重入粒度测试，调用父类的方法
 */
public class SynchronizedSuperClass12 {
    public synchronized void doSomething(){
        System.err.println("父类");
    }

}
class TestClass extends SynchronizedSuperClass12{
    @Override
    public synchronized void doSomething(){
        System.err.println("子类");
        super.doSomething();
    }
    public static void main(String[] args){
        TestClass c = new TestClass();
        c.doSomething();
    }
}