class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        
        int height = n / w;
        int[][] map = new int[height+1][w];
        
        place(n, height, w, map);
        answer = find(height, w, num, map);
        
        return answer;
    }
    
    public void place(int n, int height, int w, int[][] map) {
        int cnt = 1;
        for(int i=0; i<=height; i++) {
            if(i % 2 == 0) {
                for(int j=0; j<w && cnt <=n; j++) map[i][j] = cnt++;
            }else {
                for(int j=w-1; j>=0 && cnt <=n; j--) map[i][j] = cnt++;    
            }
        }
        
        //for(int i=0; i<=height; i++) {
        //    for(int j=0; j<w; j++) System.out.print(map[i][j] + "  ");
        //    System.out.print("\n");
        //}
    }
    
    public int find(int height, int w, int num, int[][] map) {
        for(int i=0; i<=height; i++) {
            for(int j=0; j<w; j++) {
                if(map[i][j] == num) {
                    int cnt = 0;
                    for(int k=i; k<=height; k++) {
                        if(map[k][j] == 0) return cnt;
                        cnt++;
                    }
                    return cnt;
                }
            }
        }
        return 0;
    }
}