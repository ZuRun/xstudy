package cn.zull.jvm.test.classload;

/**
 * @author zurun
 * @date 2019/10/9 18:37:41
 */
public class ParentClass {
    protected static Integer a;

    static {
        System.out.println("P - static - code block");
    }

    {
        System.out.println("P - constructor code block");
    }

    public ParentClass() {
        System.out.println("P - constructor");
    }

    private static void privateStaticMethod() {
        System.out.println("P - static - private");
    }

    public static void publicStaticMethod() {
        System.out.println("P - static - public");
    }

    private static void privateStaticMethod2() {
        System.out.println("P - static - private2");
    }

    public static void publicStaticMethod2() {
        System.out.println("P - static - public2");
    }
}
