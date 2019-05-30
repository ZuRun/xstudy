package cn.zull.jvm.ch03.classinit;

/**
 * @author zurun
 * @date 2019/5/30 20:07:24
 */
public class SubClazz extends SuperClazz {
    {
        System.out.println("[sub] 执行构造代码块...");
    }

    static {
        System.out.println("[sub] 执行静态构造代码块...");
    }
}
