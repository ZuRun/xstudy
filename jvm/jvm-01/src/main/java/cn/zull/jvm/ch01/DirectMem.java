package cn.zull.jvm.ch01;

import java.nio.ByteBuffer;

/**
 * @author zurun
 * @date 2019-05-26 11:27:19
 */
public class DirectMem {
    public static void main(String[] args) {
        directMem();
    }

    /**
     * -XX:MaxDirectMemorySize=10M 限制最大直接内存使用
     */
    public static void directMem() {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * 11);

    }
}
