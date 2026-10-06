import java.util.*;
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 1);

        for (int num : nums) {

            Map<Integer, Integer> next = new HashMap<>();

            for (Map.Entry<Integer, Integer> entry : dp.entrySet()) {

                int sum = entry.getKey();
                int ways = entry.getValue();

                next.put(
                    sum + num,
                    next.getOrDefault(sum + num, 0) + ways
                );

                next.put(
                    sum - num,
                    next.getOrDefault(sum - num, 0) + ways
                );
            }

            dp = next;
        }

        return dp.getOrDefault(target, 0);
    }
}