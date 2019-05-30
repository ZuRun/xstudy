package cn.zull.jvm.ch03.classinit;

/**
 * @author zurun
 * @date 2019/5/30 20:04:03
 */
public class SuperClazz {
    {
        System.out.println("[super] 执行构造代码块..." + sInt);
    }

    static {
        System.out.println("[super] 执行静态构造代码块...");
    }

    public int a = 11;
    public static int sInt = 90;

    public static final int sfInt1 = 22;
    public static final int sfInt2 = sInt;
}
