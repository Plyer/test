package org.ljl.test.geekbang;

import java.util.Arrays;

/**
 * @author lvjinglu
 * created at 2019/10/12
 */
public class InsertSort {

    public static void sort(Comparable[] src) {
        if (src == null || src.length == 1) {
            return;
        }

        int len = src.length;
        for (int i = 1; i < len; i++) {
            Comparable tmp = src[i];
            int j = i - 1;
            for (; j >= 0 && tmp.compareTo(src[j]) < 0; j--) {
                src[j + 1] = src[j];
            }
            src[j + 1] = tmp;
        }
    }

    public static void main(String[] args) {
        final int size = 100;
        Comparable[] src1 = new Comparable[size];
        Comparable[] src2 = new Comparable[size];

        for (int i = 0; i < size; i++) {
            int v = (int) (Math.random() * size);
            src1[i] = v;
            src2[i] = v;
        }
        System.out.println(Arrays.toString(src1));
        System.out.println(Arrays.toString(src2));
        InsertSort.sort(src1);
        System.out.println(Arrays.toString(src1));
        System.out.println(Arrays.toString(src2));
//        Arrays.sort(src2);
        System.out.println(Arrays.toString(src2));
        boolean flag = true;
        for (int i = 0; i < size; i++) {
            if (src1[i].compareTo(src2[i]) != 0) {
                flag = false;
                break;
            }
        }

        System.out.println(flag);
    }
}
