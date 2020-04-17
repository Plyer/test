package org.ljl.test.leetcode;

/**
 * @author lvjinglu
 * created at 2020/3/23
 */
public class CanJump {

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 0 , 4};
        boolean b = new Solution().canJump(nums);
        System.out.println(b);
    }

    static class Solution {

        public boolean canJump(int[] nums) {
            int[] arr = new int[nums.length];
            int len = nums.length;
            arr[len - 1] = 1;
            return canJump(0, nums, arr);
        }

        private boolean canJump(int position, int[] nums, int[] arr) {
            if (arr[position] > 0) {
                return arr[position] == 1 ? true : false;
            }

            int next = position + nums[position];
            int min = next < nums.length - 1 ? next : nums.length - 1;
            if (min == nums.length - 1) {
                arr[position] = 1;
                return true;
            }

            for (int i = min; i > position; i--) {
                if (canJump(i, nums, arr)) {
                    arr[i] = 1;
                    return true;
                }
            }

            arr[position] = 2;
            return false;
        }
    }
}
