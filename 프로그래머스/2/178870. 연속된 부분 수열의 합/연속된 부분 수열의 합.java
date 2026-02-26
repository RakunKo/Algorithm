import java.lang.Math.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        int answerCount = 1000001;
        int n = sequence.length;
        
        int[] sum = new int[sequence.length + 1];
        for (int i = 0; i < n; i++) sum[i + 1] = sum[i] + sequence[i];
        
        // 0 1 3 6 10 15
        // 0 1 2 3 5 8 12 17
        int start =0; int end =0;
        while(end < n && start <= end) {
            int curr = sum[end+1] - sum[start]; 
            
            if(curr < k) end++;
            else if(curr > k) start++;
            else {
                //System.out.println(start + " "+end);
                int currLen = end-start;
                if(currLen < answerCount) {
                    answerCount = currLen;
                    answer[0] = start;
                    answer[1] = end;
                }
                start++;
            }
        }
        
        return answer;
    }
}