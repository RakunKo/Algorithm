import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        int[][] map = new int[n][m];
        for(int i=0;i<n;i++) {
            String[] p = br.readLine().split(" ");
            for(int j=0;j<m;j++) map[i][j] = Integer.parseInt(p[j]);
        }

        Main main = new Main();
        int answer = main.solution(n, m, map);

        System.out.print(answer);
    }

    int[][] dist;

    public int solution(int n, int m, int[][] map) {
        dist = new int[n][m];
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++) dist[i][j] = -1;

        return dfs(n, m ,map, 0, 0);
    }

    int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};

    public int dfs(int n, int m, int[][] map, int x, int y) {
        if(x == n-1 && y == m-1) return 1;
        if(dist[x][y] != -1) return dist[x][y];

        dist[x][y] = 0;
        for(int i=0;i<4;i++) {
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];

            if(dx >= n ||  dx < 0 || dy >= m || dy < 0) continue;
            if(map[x][y] <= map[dx][dy]) continue;

            dist[x][y] += dfs(n, m, map, dx, dy);
        }

        return dist[x][y];
    }

}
