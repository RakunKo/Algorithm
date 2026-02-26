import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        int n = targets.length;
        
        Arrays.sort(targets, (a, b) -> a[0] - b[0]);
        
        int start = 0; int end = 100000000;
        for(int[] target: targets) {
            int s = target[0]; int e = target[1];
            
            if(start >= e || end <= s) {
                answer++;
                start = s; end = e;
            } else {
                //내부에 값이 존재
                if(start < s) start = s;
                if(end > e) end = e;
            }
        }
        
        return answer+1;
    }
    
}