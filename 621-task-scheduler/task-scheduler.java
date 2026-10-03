class Solution {
    public int leastInterval(char[] tasks, int n) {
        int [] freq = new int[26];

        int maxFreq = 0;

        for(char task : tasks) {
            freq[task - 'A']++;
            maxFreq = Math.max(maxFreq, freq[task - 'A']);
        }

        int countMax = 0;

        for(int f : freq) {
            if(f == maxFreq) {
                countMax++;
            }
        }

        int intervals = (maxFreq - 1) * (n + 1) + countMax;

        return Math.max(tasks.length, intervals);
    }
}