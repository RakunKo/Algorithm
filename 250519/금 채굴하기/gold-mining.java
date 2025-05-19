import java.util.*;
import java.lang.Math;

public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
        
        int answer = 0;
        // Please write your code here.
        //k는 1~~~~계속 늘어나는데, K^2 + (K+1)^2까지 반복 이후에는 의미 없음
        //한 좌표에서 K를 1씩 늘리면서.. -> 이전에 나온 값은 계속 유지됨.
        //이미 손해라도 나중에 이득이 될 수도 있음 -> 최대 탐색 넓이 k = 2(n-1)
        for(int i =0;i<n;i++) {
            for(int j=0;j<n;j++) {
                int result = _search(grid, n,m, i, j);
                answer = Math.max(answer, result);
            }
        }

        System.out.print(answer);
    }
    public static int _search(int[][] grid, int n, int m, int cx, int cy) {
        int result = 0;
        for(int k=0;k<=2*(n-1);k++) {
            int gold = 0;
            for(int i=0;i<n;i++) {
                for (int j=0;j<n;j++) {
                    if(Math.abs(cx-i) + Math.abs(cy-j) <= k) {      //테두리
                        if(grid[i][j] == 1) {   //gold
                            gold++;
                            if(k*k + (k+1)*(k+1) < gold * m ) { 
                                result = Math.max(result, gold);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}