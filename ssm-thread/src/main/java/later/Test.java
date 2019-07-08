package later;

/**
 * 描述 :
 *
 * @author bo bo
 * @date 2019/5/10 13:59
 *
 * 线程中断标志;
 *
 */
public class Test implements Runnable {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Test());
        t1.start();
    }

    public void run() {
        System.out.println("t1");
        while (true){
            if (Thread.currentThread().isInterrupted()){
                System.out.println("Interrupt !");
                break;
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted When Sleep  ");
                //设置中断状态.抛出异常会清除中断标记位
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }

        }
    }
}
