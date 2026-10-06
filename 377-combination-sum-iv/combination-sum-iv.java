class Solution {
    public int combinationSum4(int[] nums, int target) {
        
        int[] dp = new int[target + 1];

        dp[0] = 1;

        for (int sum = 1; sum <= target; sum++) {

            for (int num : nums) {

                if (sum >= num) {
                    dp[sum] += dp[sum - num];
                }
            }
        }

        return dp[target];
    }
}