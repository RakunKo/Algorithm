import java.util.*;

class Solution {
    class Node {
        int time, cnt;
        Node(int time, int cnt) {
            this.time = time;
            this.cnt = cnt;
        }
    }
    
    Queue<Node> queue = new LinkedList<>();
    
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        int server = 1;
        for(int i=0;i<24;i++) {
            if(queue.peek() != null && i==queue.peek().time) server -= queue.poll().cnt;
            
            
            if(players[i] >= server * m) {
                int remain = players[i] - server * m;
                int addServer = remain / m + 1;
                
                
                queue.add(new Node(i+k, addServer));
                server += addServer;
                answer += addServer;
            }
            //System.out.println(i + " : " + players[i] + ", " + server+ ", " + answer);
        }
        
        return answer;
    }
    
}