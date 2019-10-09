package cn.zull.jvm.test.classload;

/**
 * @author zurun
 * @date 2019/10/9 18:37:05
 */
public class ClassLoadTest extends ParentClass {
    static {
        System.out.println("C - static - code block");
        System.out.println(a);
    }

    {
        System.out.println("C - constructor code block");
    }

    public ClassLoadTest() {
        System.out.println("C - constructor");
    }

    private static void privateStaticMethod() {
        System.out.println("C - static - private");
    }

    public static void publicStaticMethod() {
        System.out.println("C - static - public");
    }

    private static void privateStaticMethod2() {
        System.out.println("C - static - private2");
    }

    public static void main(String[] args) throws Exception {
//        new ClassLoadTest();
//        ClassLoadTest.publicStaticMethod();
//        ClassLoadTest.publicStaticMethod2();
//        Class.forName(ClassLoadTest.class.getName());
        Class.forName(ClassLoadTest.class.getName(), false, ClassLoadTest.class.getClassLoader());
        //  反射
//        ClassLoadTest c = (ClassLoadTest) Class.forName(ClassLoadTest.class.getName()).newInstance();
//        c.publicStaticMethod();

    }
}
