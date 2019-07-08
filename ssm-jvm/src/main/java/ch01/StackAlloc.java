package ch01;

/**
 * @author bo bo
 * @date 2019/6/29 13:16
 * @desc
 */
public class StackAlloc {

    public static class User {
        public int id = 0;
        public String name = "";

    }

    public static void alloc() {
        User u = new User();
        u.id = 5;
        u.name = "mark";
    }

    public static void main(String[] args) {
        long b = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            alloc();
        }

        long e = System.currentTimeMillis();
        System.out.println((e - b) + "ms");
    }
}
