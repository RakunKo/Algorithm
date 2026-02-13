import java.util.*;

class Solution {
    class Process {
        int index;
        int priority;
        public Process(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }
    
    public int solution(int[] priorities, int location) {
        Queue<Process> q = new LinkedList<>();
        int[] vis = new int[priorities.length];
        
        for(int i=0;i<priorities.length;i++) {
            q.offer(new Process(i, priorities[i]));
        }
        
        int cnt = 1;
        while(!q.isEmpty()) {
            Process current = q.poll();
            boolean hasHigher = false;
            
            for (Process p : q) {
                if (p.priority > current.priority) {
                    hasHigher = true;
                    break;
                }
            }
            
            if(hasHigher) q.offer(current);
            else {
                if(current.index == location) return cnt;
                cnt++;
            }
        }
        return 0;
    }
}