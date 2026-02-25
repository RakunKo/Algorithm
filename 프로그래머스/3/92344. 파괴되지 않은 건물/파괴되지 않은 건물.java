class Solution {
    public int solution(int[][] board, int[][] skills) {
        int N = board.length;
        int M = board[0].length;
        
        int[][] diff = new int[N+1][M+1];
        
        for(int[] skill : skills) {
            int type = skill[0];
            int x1 = skill[1];
            int y1 = skill[2];
            int x2 = skill[3];
            int y2 = skill[4];
            int degree = skill[5];
            
            int val = (type == 1) ? -degree : degree;
            
            diff[x1][y1] += val;
            diff[x1][y2+1] -= val;
            diff[x2+1][y1] -= val;
            diff[x2+1][y2+1] += val;
        }
        
        for(int i=0;i<N;i++){
            for(int j=1;j<M;j++){
                diff[i][j] += diff[i][j-1];
            }
        }
        
        for(int j=0;j<M;j++){
            for(int i=1;i<N;i++){
                diff[i][j] += diff[i-1][j];
            }
        }
        
        int answer = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                board[i][j] += diff[i][j];
                if(board[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
}