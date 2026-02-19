import java.lang.Math.*;

class Solution {
    public int solution(String name) {
        int answer = 0;
        int L = name.length();
        int move = L - 1;
        
        for(int i=0;i<L;i++) {
            char alpha = name.charAt(i);
            
            answer += Math.min(alpha - 'A', 'Z' - alpha + 1);
            
            int next = i + 1;
            while(next < L && name.charAt(next) == 'A') next++;
            
            move = Math.min(move, 2*i + (L - next));
            move = Math.min(move, 2*(L - next) + i);
        }
        
        return answer + move;
    }
}