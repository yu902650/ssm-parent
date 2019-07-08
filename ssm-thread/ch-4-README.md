
###显式锁
Lock接口和核心方法
lock();
unlock();
tryLock();


/**
 * @author bo bo
 * @date 2019/6/28 10:05
 * @desc 显式锁的范式
 */
public class LockDemo {

    private Lock lock = new ReentrantLock();
    private int count;
    
    public void increament(){
        lock.lock();
        try {
            count++;
        }finally {
            lock.unlock();
        }
    }
}

####Lock接口和sycn的比较
**sycn**代码比较简洁
**Lock显式锁**:获取锁可以被中断.超时的获取锁,尝试获取锁.
不是特别必要的时候,尽量使用sync;减少使用lock

###可重入锁ReentrantLock 公平锁和非公平锁
如果在时间上,先对锁进行获取请求,一定先被满足,这个锁就是公平锁,反之则为不公平锁.
非公平锁的效率一般来讲会更高.公平锁

ReadWriteLock接口和读写锁ReentrantReadWriteLock
ReentrantReadWriteLock和Sync关键字,都是排它锁, 
读写锁:同一时刻运行,允许多个读线程同时访问,但是写线程访问的时候,所有的读和写都被阻塞(适合读多写少的情景)
