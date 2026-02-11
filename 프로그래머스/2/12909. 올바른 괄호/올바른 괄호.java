import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        Deque<Character> dq = new ArrayDeque<>();
        
        for(char c: s.toCharArray()) {
            if(c == '(') dq.addLast(c);
            else {
                if(dq.isEmpty()) return false;
                
                if(dq.peekLast() == '(') dq.pollLast();
                else return false;
            }
        }
        
        return dq.isEmpty();
    }
}