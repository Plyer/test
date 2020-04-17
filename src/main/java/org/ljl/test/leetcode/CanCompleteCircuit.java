package org.ljl.test.leetcode;

/**
 * @author lvjinglu
 * created at 2020/3/23
 */
public class CanCompleteCircuit {

    public static void main(String[] args) {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        int i = new Solution().canCompleteCircuit(gas, cost);
        System.out.println(i);
    }

    static class Solution {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            if (gas.length == 0) {
                return -1;
            }

            int len = gas.length;
            for (int i = 0; i < len; i++) {
                if (gas[i] >= cost[i]) {
                    int now = 0;
                    int index = i;
                    boolean flag = true;
                    for (int j = 0; j < len; j++) {
                        now += gas[index] - cost[index];
                        if (now >= 0) {
                            index = (index + 1) % len;
                        } else {
                            flag = false;
                            if (index > i) {
                                i = index;
                            } else {
                                return -1;
                            }
                            break;
                        }
                    }
                    if (flag) {
                        return index;
                    }
                }
            }
            return -1;
        }
    }
}
