package com.example.wechar;

import java.sql.SQLOutput;

/**
 * @author bo bo
 * @date 2019/6/22 8:58
 * @desc
 */
public class Test {

    public static void main(String[] args) {
        //String
        String a = "abc";
        String b = "abc";
        System.out.println(a == b);
        System.out.println(a.equals(b) + "      a.equals(b)");
        String c = new String("abc");
        String d = new String("abc");
        System.out.println(c == d + "           c==d");
        System.out.println(c.equals(d) + "       c.equals(d)");
    }

}
