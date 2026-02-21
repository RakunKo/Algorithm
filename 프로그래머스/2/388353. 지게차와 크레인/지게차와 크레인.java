import java.util.*;

class Solution {
    char[][] containers;
    int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    
    class Pos {
        int x; int y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        int h = storage.length;
        int w = storage[0].length();
        int totalSize = h*w;
        
        containers = new char[h+2][w+2];
        
        for(int i=1;i<h+1;i++) {
            char[] arr = storage[i-1].toCharArray();
            for(int j=1;j<w+1;j++) containers[i][j] = arr[j-1];
        }
        
        for(String request: requests) {
            boolean type = false;
            if(request.length() > 1) type = true;
            
            answer += release(request.charAt(0), type, h, w);
        }
        
        
        return totalSize - answer;
    }
    
    public int release(char c, boolean type, int h, int w) {
        int cnt = 0;
        if(type) {
            for(int i=1;i<=h;i++) {
                for(int j=1;j<=w;j++) {
                    if(containers[i][j] == c) {
                        containers[i][j] = ' ';
                        cnt++;
                    }
                }
            }
        }
        else {
            boolean[][] outside =  new boolean[h+2][w+2];
            boolean[][] willRemove = new boolean[h+2][w+2];
            Queue<Pos> q = new LinkedList<>();
            List<Pos> removeList = new ArrayList<>();

            q.offer(new Pos(0,0));
            outside[0][0] = true;
            
            while(!q.isEmpty()) {
                Pos p = q.poll();
                
                for(int i=0;i<4;i++) {
                    int dx = p.x + dir[i][0];
                    int dy = p.y + dir[i][1];
                    
                    if(dx < 0 || dx >= h+2 || dy < 0 || dy >= w+2) continue;
                    if(outside[dx][dy]) continue;
                    if(containers[dx][dy] >= 'A' && containers[dx][dy] <= 'Z') {
                        if(containers[dx][dy] == c) {
                            removeList.add(new Pos(dx, dy));
                            willRemove[dx][dy] = true;
                        }
                        continue;
                    }
                    
                    q.offer(new Pos(dx, dy));
                    outside[dx][dy] = true;
                }
            }
            
            for(int i=1;i<=h;i++) {
                for(int j=1;j<=w;j++) {
                    if(willRemove[i][j]) {
                        containers[i][j] = ' ';
                        cnt++;
                    }
                }
            }
        }
        
        return cnt;
    }
}