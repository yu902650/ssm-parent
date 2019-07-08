package com.xiangxue.ch1.bobotest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author bo bo
 * @date 2019/6/21 10:18
 * @desc 实现Callable接口, 允许有返回值
 */
public class ImplCallable {

    private static class UseCallable implements Callable {

        @Override
        public Object call() throws Exception {
            System.out.println("我实现了Callable method ");
            return "CallResult";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        UseCallable useCallable = new UseCallable();
        FutureTask<String> futureTask = new FutureTask<String>(useCallable);
        new Thread(futureTask).start();
        //get method 是阻塞的,会等待执行完毕
        System.out.println("callback" + futureTask.get());
    }
}
