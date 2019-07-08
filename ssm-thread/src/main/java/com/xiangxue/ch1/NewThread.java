package com.xiangxue.ch1;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author bo bo
 * @date 2019/6/6 15:33
 * @desc
 */
public class NewThread {

    /*  扩展Thread类     */


    /*  实现Runnable接口  */
    private static class UseRun implements Runnable{

        @Override
        public void run() {
            System.out.println("I am implements Runnable");
        }
    }

    /*  实现Callable接口,允许有返回值 */
    private static class UseCallable implements Callable<String> {


        @Override
        public String call() throws Exception {
            System.out.println("I am implements Callable");
            return "CallResult";
        }
    }



    public static void main(String[] args) throws Exception{
        //启动
        UseRun useRun = new UseRun();
        Thread t = new Thread(useRun);
        t.interrupt();

        UseCallable useCallable = new UseCallable();
        FutureTask<String> futureTask = new FutureTask<String>(useCallable);
        new Thread(futureTask).start();

        System.out.println( "callback : "+ futureTask.get() );  //get method 是阻塞的,会等待执行完毕


    }

}
