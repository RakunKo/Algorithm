import java.util.*;

class Solution {
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        //항상 bridge_length 개수만큼 크기 유지
        Queue<Integer> q = new LinkedList<>();
        
        for(int i=0;i<bridge_length;i++) q.offer(0);
        
        int index = 0;
        int currentWeight = 0;
        int time = 0;
        
        while(index < truck_weights.length) {
            time++;
            
            currentWeight -= q.poll();
            
            if(currentWeight + truck_weights[index] <= weight) {
                currentWeight += truck_weights[index];
                q.offer(truck_weights[index]);
                index++;
            } 
            else q.offer(0);
        }
        
        return time + bridge_length;
    }
}