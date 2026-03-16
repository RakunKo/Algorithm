import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        int n = s.length();
        
        Deque<Character> dq = new ArrayDeque<>();
        
        for(int i=0;i<n;i++) {
            s = s.substring(1) + s.charAt(0);
            dq.clear();
            
            for (int j = 0; j < n; j++) {
                char current = s.charAt(j);

                if (!dq.isEmpty() && is_valid(dq.peek(), current)) dq.pop();
                else dq.push(current);
            }
            
            if(dq.size() == 0) answer++;
        }
        
        return answer;
    }
    
    public boolean is_valid(int peek, int curr) {
        if((peek == '(' && curr == ')') 
          || (peek == '{' && curr == '}')
          || (peek == '[' && curr == ']')) return true;
        else return false;
    }
}