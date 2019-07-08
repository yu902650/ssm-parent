栈上分配:
跟着函数调用自行销毁,提高性能
需要:逃逸分析

public class A{
    public int test(int x, int y){
         String x = "";
         User u = ...;
         test2();  
         //...
       ...
    return u ;
    }
}

-server JVM 运行的模式,server才能进行逃逸分析. mix/client
-Xmx10m 和 -Xms10m :堆的大小
-XX:+DoEscapeAnalysis : 启用逃逸分析
-XX:+PrintGC :打印GC日志
-XX:-EliminateAllocations :标量替换
-XX:-UseTLAB:

+代表开启这个功能,-代表关闭功能

TLAB: ThreadLocalAllocBuffer :事先在堆里为每个线程分配一块私有内存

#标量替换,逃逸分析

##虚拟机中的对象

划分内存:指针碰撞和空闲列表       并发安全问题: 用CAS配上失败重试本地线程分配缓冲.
            
检查加载--> 分配内存--> 内存空间初始化-->设置-->初始化对象

##对象的内存布局
对象头:
对象自身的运行时数据类型指针

实例数据:
程序代码中定义的各种类型的字段内容

不是8的整数倍,用对齐填充方法补全.

对象的访问定位:
使用句柄:对象实例数据 + 对象类型数据 
直接指针:Hostpot虚拟机使用的直接指针

java.lang.OutOfMemoryError: GC overhead limit exceeded   某个循环里不停的分配对象,但是分配的太多,把堆撑爆了.

java.lang.OutOfMemoryError: Java heap space              在分配的时候,有巨型对象在分配,超出了堆的大小

Error occurred during initialization of VM               虚拟机内存太小,抛出的异常
java.lang.OutOfMemoryError: Metaspace
	<<no stack trace available>>

java.lang.StackOverflowError                             要考虑🤔是否有无限递归

树的算法: 递归 , 非递归   ``面试题???``

GC和内存溢出,只发生在Java堆和方法区


