import java.util.*;
import java.lang.Math;

public class Main {
    static int[] vis;
    static int[] visInfo;
    static int result = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        vis = new int[1001]; //방문 기록
        visInfo = new int[n]; //방문 기록
        int[][] segments = new int[n][2];
        for (int i = 0; i < n; i++) {
            segments[i][0] = sc.nextInt();
            segments[i][1] = sc.nextInt();
        }
        // Please write your code here.
        dfs(0, segments, n, 0);
        System.out.println(result);
    }
    //선분을 모두 뽑지 않아도 된다.
    public static void dfs(int depth, int[][] segments, int n, int cnt) {
        result = Math.max(result, cnt);
        for (int i = 0; i < n; i++) {
            //이미 선택한 선분은 건너뜀
            if(visInfo[i] == 1) continue;
            int segFir = segments[i][0];
            int segSec = segments[i][1];
            boolean flag = true;
            for(int j=segSec;j>=segFir;j--) {
                //뽑기 불가
                if(vis[j]==1) flag = false;
            }
            if(flag) {  //뽑기 가능
                for(int j=segSec;j>=segFir;j--) {
                    vis[j] = 1;
                }
                visInfo[i] = 1;
                dfs(depth+1, segments, n, cnt+1);
                //되돌리기
                for(int j=segSec;j>=segFir;j--) {
                    vis[j] = 0;
                }
                visInfo[i] = 0;
            }
        }
    }
}