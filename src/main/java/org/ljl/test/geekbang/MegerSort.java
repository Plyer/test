package org.ljl.test.geekbang;

import java.util.Arrays;

/**
 * @author lvjinglu
 * created at 2019/10/12
 */
public class MegerSort {
    public void megerSort(Comparable[] arr) {
        megerSort(arr, 0, arr.length - 1);
    }

    private void megerSort(Comparable[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        megerSort(arr, lo, mid);
        megerSort(arr, mid + 1, hi);
        meger(arr, lo, hi, mid);
    }

    private void meger(Comparable[] arr, int lo, int hi, int mid) {
        Comparable[] tmp = new Comparable[hi - lo + 1];
        int i = lo;
        int j = mid + 1;
        int index = 0;
        while (i <= mid && j <= hi) {
            if (arr[i].compareTo(arr[j]) <= 0) {
                tmp[index++] = arr[i++];
            } else {
                tmp[index++] = arr[j++];
            }
        }

        while (i <= mid) {
            tmp[index++] = arr[i++];
        }

        while (j <= hi) {
            tmp[index++] = arr[j++];
        }

        System.arraycopy(tmp, 0, arr, lo, tmp.length);
    }

    public static void main(String[] args) {
        MegerSort sort = new MegerSort();
        Comparable[] arr = new Comparable[10000];
        Comparable[] arr2 = new Comparable[10000];
        for (int i = 0; i < 10000; i++) {
            int val = (int) (Math.random() * 10000);
            arr[i] = val;
            arr2[i] = val;
        }
        System.out.println(Arrays.toString(arr));
        sort.megerSort(arr);
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr2);
        for (int i = 0; i < 10000; i++) {
            if (0 != arr[i].compareTo(arr2[i])) {
                System.out.println(false);
                break;
            }
        }
        System.out.println(true);
    }
}
