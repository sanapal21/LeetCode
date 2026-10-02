import java.util.*;
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();

        for(int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] bucket = new List[nums.length + 1];

        for(int num : freq.keySet()) {
            int count = freq.get(num);

            if(bucket[count] == null) {
                bucket[count] = new ArrayList<>();
            }

            bucket[count].add(num);
        }

        int[] result = new int[k];
        int index = 0;

        for(int i = bucket.length -1; i>=0 && index<k; i--) {
            if(bucket[i] != null) {
                for(int num : bucket[i]) {
                    result[index++] = num;

                    if(index == k) {
                        break;
                    }
                }
            }
        }

        return result;
    }
}