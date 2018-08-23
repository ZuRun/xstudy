/**
 * @author zurun
 * @date 2018/8/22 22:50:52
 */
public class NewThread {
    /**实现Runnable接口*/
    private static class UseRun implements Runnable {
        @Override
        public void run() {
            System.out.println("I am implements Runnable");
        }
    }

    public static void main(String[] args) {
        UseRun useRun = new UseRun();
        new Thread(useRun).start();
        // java lambda方式
        new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!"))
                .start();
    }
}
