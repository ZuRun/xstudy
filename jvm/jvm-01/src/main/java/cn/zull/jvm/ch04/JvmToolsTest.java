package cn.zull.jvm.ch04;

/**
 * @author zurun
 * @date 2019/6/18 20:51:16
 */
public class JvmToolsTest {
    public static void main(String[] args) throws InterruptedException {
        Long sum = 0L;
        while (true) {
            sum = sum + 11111;
            new Object();
            Thread.sleep(1L);
        }

    }
}
