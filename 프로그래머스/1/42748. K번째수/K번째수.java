import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for(int p=0;p<commands.length;p++) {
            int i = commands[p][0];
            int j = commands[p][1];
            int k = commands[p][2];
            
            List<Integer> list = new ArrayList<>();
            for(int cur=i-1;cur<j;cur++) list.add(array[cur]);
            list.sort((a,b) -> a - b);
            
            answer[p] = list.get(k-1);
        }
        
        return answer;
    }
}