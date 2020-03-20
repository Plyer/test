package org.ljl.test.leetcode;

/**
 * @author lvjinglu
 * created at 2020/3/20
 */
public class RemoveDuplicates2 {

    static class Solution {
        public int removeDuplicates(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int s = 0;
            int count = 1;
            for (int f = 1; f < nums.length; f++) {
                if (nums[f] == nums[s]) {
                    if (++count <= 2) {
                        nums[++s] = nums[f];
                    }
                } else {
                    count = 1;
                    nums[++s] = nums[f];
                }
            }
            return s + 1;
        }
    }
}
