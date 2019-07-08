package ch01;

import java.nio.ByteBuffer;

/**
 * @author bo bo
 * @date 2019/7/1 16:39
 * @desc
 */
public class DirectMem {

    public static void main(String[] args) {
        // nio
        // allocateDirect()  提供直接分配内存的方法
        ByteBuffer b = ByteBuffer.allocateDirect(1024*1024*14);

    }

}
