package cn.zull.jvm.ch02;

/**
 * @author zurun
 * @date 2019-05-25 20:39:52
 */
public class NewSize {

    /**
     * -Xms20M -Xmx20M -XX:+PrintGCDetails
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        int cap = 1 * 511 * 1024;
        for (int i = 0; i < 310; i++) {
//            byte[] b1 = new byte[cap];
        }
//        while (true){
//            Thread.sleep(100L);
//        }
//        byte[] b1 = new byte[cap];
//        byte[] b2 = new byte[cap];
//        byte[] b3 = new byte[cap];
//        byte[] b4 = new byte[cap];
//        byte[] b5 = new byte[cap];
//        byte[] b6 = new byte[cap];
//        byte[] b7 = new byte[cap];
//        byte[] b8 = new byte[cap];
//        byte[] b9 = new byte[cap];
//        byte[] b10 = new byte[cap];
        Thread.getAllStackTraces().forEach(((thread, stackTraceElements) -> {
            System.out.println(thread.getName()+"-"+thread.getId()+"  - "+thread.getState());
            for (StackTraceElement stackTraceElement : stackTraceElements) {
                System.out.println(stackTraceElement);
            }
        }));
    }
}
