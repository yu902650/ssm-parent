package ch01;

import java.util.LinkedList;
import java.util.List;

/**
 * @author bo bo
 * @date 2019/6/29 14:55
 * @desc 方法区;
 */
public class MetaSpace {


    public static void main(String[] args) {

        List<Object> list = new LinkedList<Object>();
        int i = 0;
        while (true) {
            i++;
            if (i % 10000 == 0) System.out.println("i=" + i);
            list.add(new Object());
        }

//        String[] strings = new String[100000000];

    }
}
