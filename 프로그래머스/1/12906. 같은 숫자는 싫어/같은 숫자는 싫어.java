import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int[] answer = {};
        
        Deque<Integer> dq = new ArrayDeque<>();
        
        for(int num: arr) {
            if(dq.isEmpty() || dq.peekLast() != num) dq.addLast(num);
        }
        
        return dq.stream().mapToInt(i -> i).toArray();
    }
}