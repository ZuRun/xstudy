package cn.zull.jvm.ch03.classinit;

/**
 * @author zurun
 * @date 2019/5/30 20:05:03
 */
public class Main {
    public static void main(String[] args) {
        SubClazz sub = new SubClazz();

//        SuperClazz.sInt = 23;
//        System.out.println(SuperClazz.sfInt2);
        System.out.println(SubClazz.sfInt2);
        System.out.println(SubClazz.sInt);
        System.out.println(SuperClazz.sInt);
    }
}
