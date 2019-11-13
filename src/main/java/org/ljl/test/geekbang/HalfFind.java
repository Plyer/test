package org.ljl.test.geekbang;

/**
 * @author lvjinglu
 * created at 2019/10/12
 */
public class HalfFind {

    public static int find(Comparable[] src, Comparable target) {
        if (src == null) {
            return -1;
        }

        return find(src, target, 0, src.length - 1);
    }

    public static int find2(Comparable[] src, Comparable target) {
        if (src == null) {
            return -1;
        }

        int mid;
        int low = 0;
        int high = src.length - 1;
        while (low <= high) {
            mid = low + (high - low) / 2;
            Integer midInt = (Integer) src[mid];
            int val = midInt * midInt;
            if (target.compareTo(val) == 0) {
                return mid;
            } else if (target.compareTo(val) > 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static int find(Comparable[] src, Comparable target, boolean left, boolean equals) {
        int pre = find(src, target, 0, src.length - 1, -1, left);
        if (src[pre].compareTo(target) == 0) {
            return pre;
        }
        if (left) {
            int result = pre + 1;
            if (result > 0 && result < src.length) {
                if (target.compareTo(src[result]) == 0) {
                    return result;
                }
            }
        } else {
            int result = pre - 1;
            if (result > 0 && result < src.length) {
                if (target.compareTo(src[result]) == 0) {
                    return result;
                }
            }
        }

        if (!equals) {
            return pre;
        }
        return -1;
    }

    private static int find(Comparable[] src, Comparable target, int low, int high, int pre, boolean left) {
        if (low > high) {
            return pre;
        }

        int mid = low + (high - low) / 2;
        if (left) {
            if (src[mid].compareTo(target) == 0) {
                return find(src, target, low, mid - 1, mid, left);
            }
        } else {
            if (src[mid].compareTo(target) == 0) {
                return find(src, target, mid + 1, high, mid, left);
            }
        }

        if (src[mid].compareTo(target) > 0) {
            return find(src, target, low, mid - 1, mid, left);
        } else {
            return find(src, target, mid + 1, high, mid, left);
        }
    }

    private static int find(Comparable[] src, Comparable target, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (src[mid].compareTo(target) == 0) {
            return mid;
        } else if (src[mid].compareTo(target) > 0) {
            return find(src, target, low, mid - 1);
        } else {
            return find(src, target, mid + 1, high);
        }
    }

    private static int find3(Comparable[] src, Comparable target) {
        int low = 0;
        int high = src.length;
        int mid;

        while (low <= high) {
            mid = low + (high - low) / 2;
            if (src[mid].compareTo(target) > 0) {
                high = mid - 1;
            } else if (src[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                if (mid == src.length - 1 || src[mid + 1].compareTo(target) > 0) {
                    return mid;
                }
                low = mid + 1;
            }
        }

        return -1;
    }

    private static int find4(Comparable[] src, Comparable target) {
        int low = 0;
        int high = src.length - 1;
        int mid;

        while (low <= high) {
            mid = low + (high - low) / 2;
            if (src[mid].compareTo(target) >= 0) {
                if (mid == 0 || src[mid - 1].compareTo(target) < 0) {
                    return mid;
                }
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Comparable target = 1;
        Comparable[] src = new Comparable[]{1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 6, 8, 10};
        System.out.println(find4(src, target));
    }
}
