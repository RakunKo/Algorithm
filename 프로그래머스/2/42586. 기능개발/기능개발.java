import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> list = new ArrayList<>();
        int size = speeds.length;
        
        int idx = 0;
        while(idx < size) {
            for(int i=0;i<size;i++) progresses[i] += speeds[i];

            if(progresses[idx] >= 100) {            
                int cnt = 0;
                for(int i=idx;i<size;i++) {
                    if(progresses[i] < 100) break;
                    cnt++;
                }
                list.add(cnt);
                idx += cnt;   
            }
        }
        
        return list.stream()
            .mapToInt(i -> i)
            .toArray();
    }
}