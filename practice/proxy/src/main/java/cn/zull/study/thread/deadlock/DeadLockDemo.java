package cn.zull.study.thread.deadlock;

/**
 * @author zurun
 * @date 2018/10/5 17:56:18
 */
public class DeadLockDemo {
    private static final String A = "A";
    private static final String B = "B";

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (A) {
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println("b");
                }
            }
        }, "线程1");

        Thread thread2 = new Thread(() -> {
            synchronized (B) {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (A) {
                    System.out.println("a");
                }
            }
        }, "线程2");
        thread1.start();
        thread2.start();
    }
}
