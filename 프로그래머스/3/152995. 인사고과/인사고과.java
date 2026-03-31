import java.util.*;

class Solution {
    class Human {
        int w; int c; int idx;
        public Human(int w, int c, int idx) {
            this.w = w;
            this.c = c;
            this.idx = idx;
        }
    }
    
    public int solution(int[][] scores) {
        int n = scores.length;
        List<Human> list = new ArrayList<>();
        
        for(int i=0;i<n;i++) list.add(new Human(scores[i][0], scores[i][1], i));
        Collections.sort(list, (a,b) -> {
            if(a.w == b.w) return a.c-b.c; 
            else return b.w-a.w;
        });
        
        boolean[] incentive = new boolean[n];
        int max_cowork = -1;
        
        for(Human h: list) {
            if(h.c < max_cowork) continue;
            
            max_cowork = h.c;
            incentive[h.idx] = true;
        }
        
        if(!incentive[0]) return -1;
        
        Collections.sort(list, (a,b) -> (b.w+b.c)-(a.w+a.c));
        
        int prev = -1;   
        int rank = 0;     
        int same = 0;    

        for (int i=0; i<n; i++) {
            if(!incentive[list.get(i).idx]) continue;
            
            Human h = list.get(i);
            int score = h.w + h.c;

            if (score == prev) same++; 
            else {
                rank += same + 1; 
                same = 0;          
                prev = score;
            }

            if(h.idx == 0) return rank;
        }
        
        return 0;
    }
}