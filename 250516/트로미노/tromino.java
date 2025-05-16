import java.util.Scanner;

public class Main {
    static int[][][] trom1 = {
        {{0,0},{1,0},{0,1}},
        {{0,0},{-1,0},{0,-1}},
        {{0,0},{-1,0},{0,1}},
        {{0,0},{1,0},{0,-1}}
    };
    static int[][][] trom2 = {
        {{0,0},{1,0},{2,0}},
        {{0,0},{-1,0},{-2,0}},
        {{0,0},{0,1},{0,2}},
        {{0,0},{0,-1},{0,-2}},
    };
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        int result = 0;
        // Please write your code here.
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                //trom1
                for(int k=0;k<4;k++) {
                    int t1_x,t2_x,t3_x =0;
                    int t1_y,t2_y,t3_y =0;
                    //좌표 계산
                    t1_x = i + trom1[k][0][0];
                    t1_y = j + trom1[k][0][1];


                    t2_x = i + trom1[k][1][0];
                    t2_y = j + trom1[k][1][1];


                    t3_x = i + trom1[k][2][0];
                    t3_y = j + trom1[k][2][1];

                    if(t1_x >= n || t1_x < 0 || t1_y >=n || t1_y < 0||
                    t2_x >= n || t2_x < 0 || t2_y >=n || t2_y < 0||
                    t3_x >= n || t3_x < 0 || t3_y >=n || t3_y < 0) {
                        //범위 초과
                        continue;
                    }
                    int sum = grid[t1_x][t1_y] + grid[t2_x][t2_y] + grid[t3_x][t3_y];
                    if(result < sum) result = sum;
                }

                for(int k=0;k<4;k++) {
                    int t1_x,t2_x,t3_x =0;
                    int t1_y,t2_y,t3_y =0;
                    //좌표 계산
                    t1_x = i + trom2[k][0][0];
                    t1_y = j + trom2[k][0][1];


                    t2_x = i + trom2[k][1][0];
                    t2_y = j + trom2[k][1][1];


                    t3_x = i + trom2[k][2][0];
                    t3_y = j + trom2[k][2][1];

                    if(t1_x >= n || t1_x < 0 || t1_y >=n || t1_y < 0||
                    t2_x >= n || t2_x < 0 || t2_y >=n || t2_y < 0||
                    t3_x >= n || t3_x < 0 || t3_y >=n || t3_y < 0) {
                        //범위 초과
                        continue;
                    }
                    int sum = grid[t1_x][t1_y] + grid[t2_x][t2_y] + grid[t3_x][t3_y];
                    if(result < sum) result = sum;
                }
            }
        }
        System.out.print(result);
    }
}