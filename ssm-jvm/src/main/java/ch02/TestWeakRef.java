package ch02;
import	java.lang.ref.WeakReference;

import java.lang.ref.SoftReference;

/**
 * @author bo bo
 * @date 2019/7/2 0:01
 * @desc
 */
public class TestWeakRef {

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
        WeakReference <User> userSoft = new WeakReference<User>(u);
        u = null;

        System.out.println(userSoft.get());
        System.gc();
        System.out.println("After GC");
        System.out.println(userSoft.get());


    }

}
