package com.xiangxue.ch1;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author bo bo
 * @date 2019/6/6 15:25
 * @desc java 虚拟机线程管理系统的接口
 */
public class OnlyMain {
    public static void main(String[] args) {
        //java 虚拟机线程管理系统的接口
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo []threadInfos = threadMXBean.dumpAllThreads(false,false);
        for(ThreadInfo threadInfo : threadInfos){
            System.out.println("[" + threadInfo.getThreadId() + "]  " + "["  + threadInfo.getThreadName() + "]");
        }
    }
}

/**
 * [6]  [Monitor Ctrl-Break]
 * [5]  [Attach Listener]     监听
 * [4]  [Signal Dispatcher]   分发
 * [3]  [Finalizer]
 * [2]  [Reference Handler]
 * [1]  [main]
 */
