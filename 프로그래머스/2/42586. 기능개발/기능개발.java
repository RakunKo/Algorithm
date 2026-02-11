import java.util.*;
import java.lang.Math.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> list = new ArrayList<>();
        int size = speeds.length;
        int[] remain = new int[size];
        
        for(int i=0;i<size;i++) {
            remain[i] = (int) Math.ceil((double)(100 - progresses[i]) / speeds[i]);
        }
        
        int idx = 0;
        while (idx < size) {
            int cnt = 1;
            int currentStandard = remain[idx]; 
            int j = idx + 1;

            while (j < size && remain[j] <= currentStandard) {
                cnt++;
                j++;
            }

            list.add(cnt);
            idx = j; 
        }
        
        return list.stream()
            .mapToInt(i -> i)
            .toArray();
    }
}