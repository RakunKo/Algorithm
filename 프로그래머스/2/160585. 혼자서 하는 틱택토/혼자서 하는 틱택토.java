class Solution {
    int MAX = 3;
    int[][] vis = new int[MAX][MAX];
    
    public int solution(String[] board) {
        int O = 0; int X = 0;
        for(int i=0;i<MAX;i++) {
            for(int j=0;j<MAX;j++) {
                if(board[i].charAt(j) == 'O') O++;
                else if(board[i].charAt(j) == 'X') X++;
            }
        }
        
        int oWin = isEnd(board, 'O'); 
        int xWin = isEnd(board, 'X');
        
        if (X > O || O - X > 1) return 0;
        if (oWin > 0 && xWin > 0) return 0;
        if (oWin > 0 && O != X + 1) return 0;
        if (xWin > 0 && O != X) return 0;
        
        return 1;
    }
    
    public int isEnd(String[] board, char type) {
        int cnt = 0;
        for(int i=0;i<MAX;i++) {
            if(board[i].charAt(0) == type && board[i].charAt(1) == type && board[i].charAt(2) == type) cnt++;
            if(board[0].charAt(i) == type && board[1].charAt(i) == type && board[2].charAt(i) == type) cnt++;
        }
        
        if(board[0].charAt(0) == type && board[1].charAt(1) == type && board[2].charAt(2) == type) cnt++;
        if(board[0].charAt(2) == type && board[1].charAt(1) == type && board[2].charAt(0) == type) cnt++;
        
        return cnt;
    }
    
}