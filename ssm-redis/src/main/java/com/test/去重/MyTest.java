package com.test.去重;

/**
 * Created by bobo on 2019/4/26 9:16
 */

import java.util.ArrayList;
import java.util.List;

//测试类主方法
public class MyTest {
    public static void main(String[] args) {
        List<UserEntity> list = new ArrayList<UserEntity>();
        UserEntity u1 = new UserEntity();
        u1.setId(1);
        u1.setName("A");
        u1.setQuantity(1);

        UserEntity u2 = new UserEntity();
        u2.setId(1);
        u2.setName("B");
        u2.setQuantity(2);

        UserEntity u3 = new UserEntity();
        u3.setId(1);
        u3.setName("A");
        u3.setQuantity(3);
        list.add(u1);
        list.add(u2);
        list.add(u3);

        List<UserEntity> resultList = new ArrayList<UserEntity>();
        for (UserEntity _o : list) {
            boolean isFind = false;
            for(int i=0;i<resultList.size();i++){
                if(_o.getName().equals(resultList.get(i).getName())){
                    isFind = true;
                    resultList.get(i).setQuantity(resultList.get(i).getQuantity() + _o.getQuantity());
                }
            }
            if(!isFind){
                resultList.add(_o);
            }
        }

        for (UserEntity _r : resultList) {
            System.out.println(_r.getName()+","+_r.getQuantity());
        }
    }
}