##2.线程的并发工具类.
Fork-Join体现了'分而治之'
Fork-Join框架:就是在必要的情况下,将一个大任务,进行拆分(fork) 成
若干个小任务(拆到不可再拆).再将一个个的小任务运算的结果进行join汇总

- 什么是分而治之?
规模为N的问题,N<阈值,直接解决;
N>阈值,将N分解为K个小规模子问题,子问题互相独立,与原问题形式相同;
将子问题的解合并得到原问题的解动态规范

工作密取

Fork/Join 使用标准范式
z_pic Fork-Join标准范式.png

分而治之是一种算法思想


###常用的并发工具类
CountDownLatch
作用:是一个线程等待其他的线程完成工作以后在执行,加强版的jion.
await用来等待,countDown负责计数器-1 

CyclicBarrier
让一组线程到达某个屏障,被阻塞,一直到组内最后一个线程到达屏障时,屏障开放,所有被阻塞的线程会继续运行
CyclicBarrier(int parties, Runnable barrierAction),屏障开放,barrierAction定义的任务会执行,

###CountDownLatch和CyclicBarrier的区别:
手动挡  CountDownLatch  放行由第三者控制.
自动挡  CyclicBarrier   放行由一组线程本身控制.
1.countDownLatch放行由第三者控制,CyclicBarrier放行由一组线程本身控制,
2.countDownLatch放行条件 >= 线程数 , CyclicBarrier放行条件=线程数

Semaphore:
控制同时访问某个特定的资源的线程数量,用在流量控制.

Exchanger:
```两个```线程间的数据交换 ,setA setB  阻塞方法

Callable , Future 和 FutrueTask
isDone              结束,正常还是异常结束,或者自己取消,返回 false
isCancelled         任务完成前被取消,返回true
cancel(boolean mayInterruptIfRunning);    尝试去终止任务

1.任务没有开始,,返回false,
2.任务已经启动,cance(true),中断成功,返回true,cancel(false),不会去中断已经运行的任务
3.任务已经结束,返回false;

文档:图片(云),文字

并发工具类的使用场景.
CountDownLatch 和CyclicBarrier 的区别:





