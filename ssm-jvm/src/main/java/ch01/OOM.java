package ch01;

import java.util.LinkedList;
import java.util.List;

/**
 * @author bo bo
 * @date 2019/6/29 14:42
 * @desc
 */
public class OOM {

    public static void main(String[] args) {
        /**
         * 某个循环里不停的分配对象,但是分配的太多,把堆撑爆了.
         * java.lang.OutOfMemoryError: GC overhead limit exceeded
         */
//        List<Object> list = new LinkedList<Object>();
//        int i = 0;
//        while (true) {
//            i++;
//            if (i % 10000 == 0) System.out.println("i=" + i);
//            list.add(new Object());
//        }

        /**
         * 在分配的时候,有巨型对象在分配,超出了堆的大小
         * java.lang.OutOfMemoryError: Java heap space
         */
        String[] strings = new String[100000000];

    }

}
