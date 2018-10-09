package cn.zull.study.basis.utils;

import java.util.Scanner;

/**
 * @author zurun
 * @date 2018/10/6 00:57:29
 */
public class InputUtils {

    public static String getInputString() {
        Scanner input = new Scanner(System.in);
        String string = input.nextLine();
        input.close();
        return string;
    }
}
