import java.lang.Math.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        int answerCount = 1000001;
        
        int[] sum = new int[sequence.length + 1];
        sum[0] = sequence[0];
        for(int i=1;i<sequence.length;i++) sum[i] = sum[i-1] + sequence[i];
        
        // 1 3 6 10 15
        // 1 2 3 5 8 12 17
        int start =0; int end =0;
        while(end < sequence.length && start <= end) {
            
            if(sum[end] - sum[start] + sequence[start] < k) end++;
            else if(sum[end] - sum[start] + sequence[start] > k) start++;
            else {
                //System.out.println(start + " "+end);
                if(end-start < answerCount) {
                    answerCount = end-start;
                    answer[0] = start;
                    answer[1] = end;
                }else if(end-start == answerCount) {
                    if(answer[0] > start + 1) {
                        answerCount = end-start;
                        answer[0] = start;
                        answer[1] = end;
                    }
                }
                start++;
            }
        }
        
        return answer;
    }
}