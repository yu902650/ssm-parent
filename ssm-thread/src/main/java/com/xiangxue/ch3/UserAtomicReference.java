package com.xiangxue.ch3;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author bo bo
 * @date 2019/6/25 16:22
 * @desc 引用类型的原子操作类
 */
public class UserAtomicReference {

    static AtomicReference<UserInfo> userRef = new AtomicReference<UserInfo>();

    public static void main(String[] args) {

        /**
         * 修改实体的实例
         */
        UserInfo user = new UserInfo("Mark", 15);

        userRef.set(user);

        UserInfo updateUser = new UserInfo("Bill", 17);
        /**
         * compareAndSet (期望值,目标值)
         */
        userRef.compareAndSet(user,updateUser);

        System.out.println(userRef.get().getName());
        System.out.println(userRef.get().getAge());
        System.out.println("-------------------------------");
        System.out.println(user.getName());
        System.out.println(user.getAge());
    }

    static class UserInfo {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public UserInfo(String name, int age) {
            this.name = name;
            this.age = age;
        }


    }

}
