import java.util.*;
import java.lang.Math;

public class Main {
    static boolean[][] posFirst; 
    static boolean[][] pos; 
    static List<int[]> lad = new ArrayList<>(); 
    static int[] vis;
    static int res =100;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        posFirst = new boolean[n+2][16];
        pos = new boolean[n+2][16];
        int m = sc.nextInt();
        int maxY =0;
        for(int i = 0; i < m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            lad.add(new int[]{a,b});
            posFirst[a][b]=true;
            maxY = Math.max(maxY, b);
        }
        vis = new int[lad.size()];
        // Please write your code here.
        int[] firstResult = ladder(n, m, posFirst, maxY); 
        dfs(0, n, m, firstResult, maxY, 0);
        System.out.print(res);
        return;
    }

    public static void dfs(int depth, int n, int m, int[] firstResult, int maxY, int cnt) {
        if(cnt >= res) return; //이미 선택한 사다리가 최소보다 크면 답이 될 수 없음.

        //초기값과 같은지 비교후 같으면 결과값 초기화, 최대 depth여도 이 작업은 수행해야함.
        int[] secondResult = ladder(n,m,pos,maxY);
        if(isEqualResult(firstResult, secondResult, n)) res = Math.min(res, cnt);

        if(depth==m) return;
        
        //이 선택은 순서 상관없이 한번만 선택되어야한다.
        for(int i=depth;i<lad.size();i++) {
            if(vis[i]==1) continue; //이미 선택한 사다리면 건너뛰기
            int x = lad.get(i)[0];
            int y = lad.get(i)[1];

            pos[x][y]=true;
            vis[i] = 1;
            dfs(depth+1, n,m,firstResult, maxY, cnt+1);
            vis[i] = 0;
            pos[x][y]=false;    //복구
        }
    }

    public static int[] ladder(int n, int m, boolean[][] pos, int maxY) {
        int[] result = new int[n+2];
        for(int i=1;i<=n;i++) {
            int x = i;
            int y = 1;
            for(int j=0;j<maxY;j++) {
                if(pos[x][y]) {
                    x++;y++;
                } else if (pos[x-1][y]) {
                    x--;y++;
                } else {
                    y++;
                }
            }
            result[x] = i;
        }
        return result;
    }

    public static boolean isEqualResult(int[] fisrt, int[] second, int n) {
        boolean flag = true;
        for(int i =1;i<=n;i++) {
            if(fisrt[i]!=second[i]) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}