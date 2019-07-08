package later;

/**
 * @author bo bo
 * @date 2019/5/10 14:50
 * @desc
 */
public class JoinMain {

    public volatile static int i = 0;

    public static class AddThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 10000000; i++) ;
        }
    }

    /**
     * join 的本质
     * while(isAilve()){
     * wait(0);
     * }
     * <p>
     * 不要在Thread实例上使用wait() 和 notify()实例
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        AddThread addThread = new AddThread();
        addThread.start();
        addThread.join();
        System.out.println(i);
    }


}

