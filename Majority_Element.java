class Solution {
    public int majorityElement(int[] nums) {

        int n = nums.length;
        
        for(int i=0; i<n; i++) {
            int freq = 1;

            for(int j=i+1; j<n; j++) {
                if(nums[i] == nums[j]) {
                    freq++;
                }
            }

            if(freq > n/2) {
                return nums[i];
            }
        }

         return -1;
    }
}
