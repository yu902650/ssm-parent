###`基础概念`
CPU核心数和线程数的关系
核心数:线程数=1:1  ;使用了超线程技术后---> 1:2

CPU时间片轮转机制
又称RR调度，会导致上下文切换  5000-20000轮转周期

什么是进程和线程
进程: 程序运行资源分配的最小单位,进程内部有多个线程,会共享这个进程的资源
线程: CPU调度的最小单位

澄清并行和并发
并行:同一时刻,可以处理事情的能力
并发:时间单位内可以处理事情的能力

高并发编程的意义.好处和注意事项
共享资源,存在冲突 ; 死锁 ; 太多的线程,就有可能搞垮机器 ; 

----------------------------------------------------------------------------
  
###`认识Java里的线程`
Java里的程序天生就是多线程的,那么有几种新启线程的方式?
- 类Thread
- 接口Runnable
- 接口Callable

有开始就有结束,怎么样才能让Java里的线程安全停止工作呢?
stop()还是interrupt() . isInterrupted() .static方法interrupted(),深入理解这些方法
1.自然执行完毕
2.线程抛出异常 
3.
stop()
resume()
suspend() 挂机 --- 被调用后不会释放资源.

java 线程是协作式
interrupt() 中断一个线程,并不是强行关闭,打个招呼.
isInterrupted() 判断当前线程是否属于中断状态
static方法interrupted() 判断当前线程是否属于中断状态

========================================================2019年6月6日 16:24:07  多线程 02节课   15:06

定义变量来标识线程的状态. 这种方式在非阻塞中可以正常使用,
但是阻塞队列中使用,会出现异常, 在while判断时(flag || isInterrupted) 才会通过

了解yield() ：将线程从运行转到可运行状态

run 和 start 区别:

run 当成普通方法调用
start线程方法调用

线程的优先级: 1~10  缺省为5  setPriority(10); //设置线程的优先级.操作系统不同,优先级设定不同,尽量不用

守护线程:  和主线程共死的. 样例:OnlyMain ,DaemonThread
守护线程设置要在start前
守护线程里的try finally不能保证一定执行.

什么是线程间的共享?
synchronized内置锁🔒
用处
对象锁
类锁 --- 锁的是这个类的Class对象
sync 和spring 集成, 如果spring允许创建多个对象,那个锁的时候可能锁的不是一个对象

##sync既确保可见性又保证原子性,volatile只保证可见性,不保证原子性

volatile关键字,最轻量的同步机制.
volatile适用只有一个线程写, 多个线程读

ThreadLocal的使用. 线程变量


synchronized 
volatile
ThreadLocal

线程间协作
等待和通知                       join方法
wait()                       调用yield(),sleep(),wait(),notify()等方法对锁有何影响
notify/notifyAll
等待和通知的标准范式
notify和notifyAll应该用谁
等待超时模式实现一个连接池

wait() 对象上的方法
notify/notifyAll 对象上的方法
等待和通知的标准范式
等待方:
    1.获取对象的锁
    2.循环里判断条件是否满足,不满足调用wait方法.
    3.条件满足执行业务逻辑
通知方:
    1.获取对象的锁
    2.改变条件
    3.通知所有等待在对象的线程

notify和notifyAll应该用谁
尽量使用notifyAll,因为尽量notify可能发生信号丢失的情况

等待超时模式实现一个连接池
假设 等待时长为T,当前时间now+T以后超时
long overtime = now+T;
long remain = T; //等待的持续时间
while(result 不满足条件 && remain > 0){
    wait(remain);
    remain = overtime-now; //等待剩下的持续时间
}
return result;

join()方法 
面试点
线程A 执行了线程B的join方法,线程A必须要等待B执行完成了以后,线程A才能继续自己的工作

面试点:
调用yield,sleep,wait,notify 等方法对锁有何影响?
线程在执行yield()以后,     持有的锁是不释放的.
sleep()                 方法被调用后,持有的锁是不释放的.
wait()                  调用wait方法之前必须持有锁 .   调用了wait()方法以后,锁就会被释放.当wait返回的时候,线程会重新尺持有锁.
notify()                调用notify方法之前必须持有锁   调用notify()方法,锁不会被释放.