package com.xiangxue.ch2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bo bo
 * @date 2019/6/20 16:34
 * @desc 内存溢出test
 */
public class Main {


    public static void main(String[] args) {

            List<byte[]> list = new ArrayList<>();
            int i=0;
            while(true){
                list.add(new byte[5*1024*1024]);
                System.out.println("分配次数："+(++i));
            }
        }

    }