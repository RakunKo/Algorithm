import java.util.*;

class Solution {
    int n,m, cnt;
    
    class Pos {
        int x; int y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;
        
        m = key.length;
        n = lock.length;
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) if(lock[i][j] == 0) cnt++;
        }
        
        if(cnt==0) return true;
        
        int[][] copy = new int[m][m];
        for(int i=0;i<m;i++) {
            for(int j=0;j<m;j++) copy[i][j] = key[i][j];
        }
        
        for(int i=0;i<4;i++) {
            if(i!=0) copy = rotation(copy);
            if(check(copy, lock)) return true;
        }
        
        return false;
    }
    
    public int[][] rotation(int[][] key) {
        int[][] rotate = new int[m][m];
        
        for(int i=0;i<m;i++) {
            for(int j=0;j<m;j++) {
                if(key[i][j] == 1) rotate[j][m-i-1] = 1;
            }
        }
        
        return rotate;
    }
    
    public boolean check(int[][] key, int[][] lock) {
        //상대적 좌표
        List<Pos> list = new ArrayList<>();
        int x = -1; 
        int y = -1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (key[i][j] == 1) {
                    if (x == -1) {
                        x = i;
                        y = j;
                    }
                    list.add(new Pos(i - x, j - y));
                }
            }
        }
        
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(lock[i][j] == 0) {
                    
                    for(int k=0;k<list.size();k++) {
                        Pos pivot = list.get(k);
                        int count = 0;
                        for(Pos p: list) {
                            int diff_i = i + pivot.x - p.x;
                            int diff_j = j + pivot.y - p.y;
                            
                            if(diff_i < 0 || diff_i >= n || diff_j < 0 || diff_j >= n) continue;
                            if(lock[diff_i][diff_j] == 1) break;
                            
                            count++;
                        }
                        
                        if(count == cnt) return true;
                    }
                    
                }
            }
        }
        
        return false;
    }
}