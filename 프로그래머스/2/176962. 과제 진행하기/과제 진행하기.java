import java.util.*;

class Solution {
    class Task{
        String name; int r;
        public Task(String name, int r) {
            this.name = name;
            this.r = r;
        }
    }
    
    public String[] solution(String[][] plans) {
        int n = plans.length;
        String[] answer = new String[n];
        
        Arrays.sort(plans, (a,b) -> a[1].compareTo(b[1]));
        
        Deque<Task> remain = new ArrayDeque<>();
        
        // music 30
        // computer 90
        // 

        // "science" "history"
        
        int idx = 0;
        for(int i=0;i<n-1;i++) {
            int diff = toTime(plans[i+1][1]) - toTime(plans[i][1]);
            if(diff < Integer.parseInt(plans[i][2])) {
                remain.push(new Task(plans[i][0], Integer.parseInt(plans[i][2]) - diff));
            }else {
                answer[idx++] = plans[i][0];
                
                diff -= Integer.parseInt(plans[i][2]);
                
                if(diff > 0) {
                    while(!remain.isEmpty()) {
                        Task t = remain.pop();
                        if(diff >= t.r) {
                            answer[idx++] = t.name;
                            diff -= t.r;
                        }else {
                            remain.push(new Task(t.name, t.r - diff));
                            break;
                        }
                    }
                }
            }
        }
        
        answer[idx++] = plans[n-1][0];
        
        while(!remain.isEmpty()) {
            Task t = remain.pop();
            answer[idx++] = t.name;
        }
        
        
        
        return answer;
    }
    
    public int toTime(String start) {
        String[] part = start.split(":");
        return Integer.parseInt(part[0])*60 + Integer.parseInt(part[1]);
    }
}