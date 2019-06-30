package com.xstudy.test.interview.question.array;

import java.util.Arrays;

/**
 * 数组顺序颠倒
 *
 * @author zurun
 * @date 2019/6/30 11:53:37
 */
public class Reserve {
    public static void reserve(int begin, int end) {
        int[] arrays = {0, 1, 2, 3, 4, 5, 6};
        System.out.println(Arrays.toString(arrays));
        int length = (end - begin) / 2;
        for (int i = 0; i <= length; i++) {
            int tmp = arrays[begin + i];
            arrays[begin + i] = arrays[end - i];
            arrays[end - i] = tmp;
        }
        System.out.println(Arrays.toString(arrays));
        System.out.println("-----");
    }

    public static void main(String[] args) {
        reserve(0, 4);
        reserve(1, 4);
        reserve(2, 4);
        reserve(3, 4);
        reserve(4, 4);
    }
}
