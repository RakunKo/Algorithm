import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.
        int result = 0;
        for(int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if(i + 2 >= n || j + 2 >= n) continue;

                int sum =0 ;
                for(int k=i;k<=i+2;k++) {
                    for (int h=j;h<=j+2;h++) {
                        if(grid[k][h] == 1) sum++;
                    }
                }

                if(result < sum) result = sum;
                sum =0;
            }
        }

        System.out.print(result);
    }
}