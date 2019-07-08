package ch02;

import java.lang.ref.SoftReference;
import java.util.LinkedList;
import java.util.List;

/**
 * @author bo bo
 * @date 2019/7/1 23:34
 * @desc
 */
public class TestSoftRef {

    public static class User {
        public int id = 0;
        public String name = "";

        public User(int id, String name) {
            super();
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        User u = new User(1, "Mark");
        //软引用 SoftReference
        SoftReference<User> userSoft = new SoftReference<User>(u);
        u = null;

        System.out.println(userSoft.get());
        System.gc(); //展示GC的时候,SoftReference不一定会被回收
        System.out.println("After GC");
        System.out.println(userSoft.get());

        List<byte[]> list = new LinkedList<>();
        try {
            for (int i = 0; i < 100; i++) {
                System.out.println("*********************" + userSoft.get());
                list.add(new byte[1024 * 1024 * 1]);
            }
        } catch (Throwable e) {

            //抛出OOM之后,打印
            System.out.println("*Throwable**************" + userSoft.get());
        }

    }


}
