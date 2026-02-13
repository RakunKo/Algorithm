import java.util.*;

class Solution {
    class Truck {
        int weight;
        int time;
        public Truck(int weight, int time) {
            this.weight = weight;
            this.time = time;
        }
    }
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> q = new LinkedList<>();
        Queue<Truck> c = new LinkedList<>();
        
        for(int wi: truck_weights) q.offer(wi);
        
        int time = 1;
        int w = q.poll();
        c.offer(new Truck(w, time));
        int currentWeight = w;
        
        
        while(!c.isEmpty()) {
            time++;
            
            if(time - c.peek().time == bridge_length) {
                currentWeight -= c.poll().weight;
            }
            
            if(!q.isEmpty()) {
                if(c.size() + 1 <= bridge_length && currentWeight + q.peek() <= weight) {
                    int wig = q.poll();
                    c.offer(new Truck(wig, time));
                    currentWeight += wig;
                }
            }
        }
        
        return time;
    }
}