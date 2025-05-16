import java.util.Scanner;
  
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
        
        int result = 0;
        // Please write your code here.
        for(int i=0;i<n;i++) {
            int tmp_x = 0;
            int tmp_y = 0;
            int prev_x = 0;
            int prev_y = 0;
            for (int j=0;j<n;j++) {
                int value_x = grid[i][j];
                if(prev_x != value_x) { //이전값과 다른 값
                    //하나의 행, 열에 있거나 없거나 이기 때문에 중복은 신경쓰지 않음
                    tmp_x = 1;
                }
                else {  //이전값과 같은값
                    tmp_x++;
                    }
                if(tmp_x >= m) {
                        result++;
                        break;
                }
                prev_x = value_x;
            }
            for (int j=0;j<n;j++) {
                int value_y = grid[j][i];
                if(prev_y != value_y) {
                    tmp_y = 1;
                }
                else {
                    tmp_y++;
                }
                if(tmp_y >= m) {
                    result++;
                    break;
                }
                prev_y = value_y;
            }
        }
        System.out.print(result);
    }
}