import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int MAX = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] map = new int[MAX][MAX];

        for(int i=0;i<MAX;i++) {
            String[] part = br.readLine().split(" ");
            for (int j = 0; j < MAX; j++)
                map[i][j] = Integer.parseInt(part[j]);
        }

        Main main = new Main();

        long result = main.solution(map);
        System.out.print(result);
    }

    boolean[][] vis = new boolean[MAX][MAX];
    int[] remain = {5,5,5,5,5,5};
    int answer = Integer.MAX_VALUE;

    public int solution(int[][] map) {

        dfs(map, 0,0,0);

        if(answer == Integer.MAX_VALUE) return -1;
        else return answer;
    }

    public void dfs(int[][] map, int x, int y, int cnt) {
        if(x == MAX) {
            answer = Math.min(answer, cnt);
            return;
        }

        if(y >= MAX) {
            dfs(map, x+1, 0, cnt);
            return;
        }
        if(map[x][y] == 0 || vis[x][y]) {
            dfs(map, x, y+1, cnt);
            return;
        }

        for(int size=1;size<=5;size++) {
            if(remain[size] == 0) continue;
            if(!is_valid(size, x, y, map)) continue;

            fill_vis(size, x, y, true);
            remain[size]--;
            dfs(map, x, y+1, cnt+1);
            fill_vis(size, x, y, false);
            remain[size]++;
        }
    }

    public boolean is_valid(int n, int x, int y, int[][] map) {

        for(int i=x;i<x+n;i++) {
            for(int j=y;j<y+n;j++) {
                if(i >= MAX || i < 0 || j >= MAX || j < 0) return false;
                if(map[i][j] == 0) return false;
                if(vis[i][j]) return false;
            }
        }

        return true;
    }

    public void fill_vis(int n, int x, int y, boolean condition) {
        for(int i=x;i<x+n;i++)
            for(int j=y;j<y+n;j++)
                vis[i][j] = condition;
    }
}
