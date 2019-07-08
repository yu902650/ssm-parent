package later;

/**
 * @author bo bo
 * @date 2019/5/10 15:45
 * @desc
 */
public class PriorityDemo {

    public static class HightPriority extends Thread {
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (PriorityDemo.class) {
                    count++;
                    if (count > 10000000) {
                        System.out.println("HightPriority is complete");

                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

    }
}
