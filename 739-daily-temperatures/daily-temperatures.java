import java.util.*;

class Solution {
    public int[] dailyTemperatures(int[] temperature) {
        int n = temperature.length;
        int[] answer = new int[n];

        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<n; i++) {
            while(!stack.isEmpty() && temperature[i] > temperature[stack.peek()]) {
                int prevIndex = stack.pop();

                answer[prevIndex] = i - prevIndex;
            }

            stack.push(i);
        }

        return answer;
    }
}