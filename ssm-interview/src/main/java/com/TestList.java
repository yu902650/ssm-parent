package com;

/**
 * Created by bobo on 2019/4/26 9:01
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestList {
    public static void main(String[] args) {
        List<Object> a = new ArrayList<Object>();
        List<Object> b = new ArrayList<Object>();
        for(int i=0;i<2;i++){
            User user = new User();
            user.setId(i+"");
            a.add(user);
        }
        for(int i=1;i<3;i++){
            User user = new User();
            user.setId(i+"");
            b.add(user);
        }
        a.removeAll(b);
        a.addAll(b);
        System.out.println(a.size());
        Set<Object> set = new HashSet<Object>();
        set.addAll(a);
        set.addAll(b);
        List<Object> c = new ArrayList<Object>(set);
        System.out.println(c.size());
    }
}