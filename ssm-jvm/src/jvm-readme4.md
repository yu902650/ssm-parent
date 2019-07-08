只发生在新生代的GC 是 minor gc
老年代的GC full gc


Cmd输入. 查看系统垃圾回收类型.
java -XX:+PrintCommandLineFlags -version


G1   JDK8

未来的垃圾回收
ZGC  JDK11
处理TB量级的堆
GC时间不超过10ms
与G1相比,应用吞吐量降低不超过15%

内容分配与回收策略
1.对象优先在Eden分配,如果内存空间不足,就会发送MinorGC.
>大对象直接进入老年代.大对象指很长的字符串和大型数组 .

2.大对象直接进入老年代
>1.导致内存有空间,提前垃圾回收来释放他们 
>2.进行大量的内存复制
> -XX:PretenureSizeThreshold 参数.大于直接在老年代分配.
缺省值为0,表示绝不会直接分配在老年代

3.长期存活的对象进入老年代
>长期存活的对象进入老年代,默认15,每熬过一次MinorGC,+1  
>-XX:MaxTenuringThreshold 参数 默认15
jdk版本不同,默认值可能不同

4.动态对象年龄判定
>

5.空间分配担保
>大量对象存活,survical空间不够.只要**老年代连续空间大于新生代对象的总大小.
或者历次晋升的平均大小**,就进行Minor GC,否则就就行Full GC

###新生代配置
新生代大小配置参数的优先级：
高：-XX:NewSize/MaxNewSize
中间 -Xmn （NewSize= MaxNewSize）
低：-XX:NewRatio  表示比例，例如=2，表示 新生代：老年代 = 1:2

-XX:SurvivorRatio 表示Eden和Survivor的比值，
缺省为8 表示 Eden:FromSurvivor:ToSurvivor= 8:1:1

同样的代码情况下：
-Xms20M -Xmx20M -XX:+PrintGCDetails –Xmn2m -XX:SurvivorRatio=2
没有垃圾回收
数组都在老年代

-Xms20M -Xmx20M -XX:+PrintGCDetails -Xmn7m -XX:SurvivorRatio=2
发生了垃圾回收
新生代存了部分数组，老年代也保存了部分数组，发生了晋升现象

-Xms20M -Xmx20M -XX:+PrintGCDetails -Xmn15m -XX:SurvivorRatio=8
新生代可以放下所有的数组
老年代没放

-Xms20M -Xmx20M -XX:+PrintGCDetails -XX:NewRatio=2
发生了垃圾回收
出现了空间分配担保，而且发生了FullGC


###内存泄漏和内存溢出辨析 
内存溢出：实实在在的内存空间不足导致；
内存泄漏：该释放的对象没有释放，多见于自己使用容器保存元素的情况下。
JDK为我们提供的工具 
jps 
列出当前机器上正在运行的虚拟机进程
-p  :仅仅显示VM 标示，不显示jar,class, main参数等信息.
-m:输出主函数传入的参数. 下的hello 就是在执行程序时从命令行输入的参数
-l: 输出应用程序主类完整package名称或jar完整名称.
-v: 列出jvm参数, -Xms20m -Xmx50m是启动程序指定的jvm参数

jstat
是用于监视虚拟机各种运行状态信息的命令行工具。它可以显示本地或者远程虚拟机进程中的类装载、内存、垃圾收集、JIT编译等运行数据，在没有GUI图形界面，只提供了纯文本控制台环境的服务器上，它将是运行期定位虚拟机性能问题的首选工具。
假设需要每250毫秒查询一次进程2764垃圾收集状况，一共查询20次，那命令应当是：jstat-gc 2764 250 20
常用参数：
-class (类加载器) 
-compiler (JIT) 
-gc (GC堆状态) 
-gccapacity (各区大小) 
-gccause (最近一次GC统计和原因) 
-gcnew (新区统计)
-gcnewcapacity (新区大小)
-gcold (老区统计)
-gcoldcapacity (老区大小)
-gcpermcapacity (永久区大小)
-gcutil (GC统计汇总)
-printcompilation (HotSpot编译统计)

jinfo 查看和修改虚拟机的参数,

代码中防止内存泄漏:
#存放数据的容器如果用完不清(特别是array),容易产生内存泄漏
#HashMap get/push 数据后,及时进行清除

管理远程进程:
-Djava.rmi.server.hostname=....
-Dcom.sun.management.jmxremote
-Dcom.sun.management.jmxremote.port=8888
-Dcom.sun.management.jmxremote.authenticate=false
-Dcom.sun.management.jmxremote.ssl=false

类指针压缩空间

visualvm.java.net

深堆和浅堆
