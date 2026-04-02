import java.util.*;
import java.io.*;
import java.lang.Math.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] part = br.readLine().split(" ");
        int n = Integer.parseInt(part[0]);
        int m = Integer.parseInt(part[1]);
        int k = Integer.parseInt(part[2]);

        int[][] map = new int[n+1][n+1];

        for(int i=1;i<=n;i++) {
            String[] p = br.readLine().split(" ");
            for(int j=1;j<=n;j++) map[i][j] = Integer.parseInt(p[j-1]);
        }

        String[] s = br.readLine().split(" ");
        int[] start = new int[]{Integer.parseInt(s[0]), Integer.parseInt(s[1])};

        int[][] persons = new int[n+1][n+1];
        int[][] arrives = new int[m+1][2];
        for(int i=1;i<=m;i++) {
            String[] p = br.readLine().split(" ");
            persons[Integer.parseInt(p[0])][Integer.parseInt(p[1])] = i;
            arrives[i][0] = Integer.parseInt(p[2]);
            arrives[i][1] = Integer.parseInt(p[3]);
        }

        Main main = new Main();
        long result = main.solution(n, m, k, map, start, persons, arrives);

        System.out.print(result);
    }

    int[][] dir = {{-1,0},{0,-1},{0,1},{1,0}};
    int sx,sy;

    public long solution(int n, int m, int k, int[][] map, int[] start, int[][] persons, int[][] arrives) {
        sx = start[0];
        sy = start[1];

        for(int i=0;i<m;i++) {
            int[] person = bfs_person(map, n, persons);
            int index = person[0];

            if(k - person[1] <= 0 || index == -1) return -1;
            k -= person[1];

            sx = person[2];
            sy = person[3];
            persons[sx][sy] = 0;

            int arrive = bfs_arrive(arrives[index][0], arrives[index][1], map, n);
            if(k - arrive < 0 || arrive == -1) return -1;

            k -= arrive;
            k += arrive*2;

            sx = arrives[index][0];
            sy = arrives[index][1];
        }

        return k;
    }

    public int[] bfs_person(int[][] map, int n, int[][] persons) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[2] != b[2]) return a[2] - b[2];
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        boolean[][] visited = new boolean[n + 1][n + 1];
        Deque<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{sx, sy, 0});
        visited[sx][sy] = true;

        int minVisitDist = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int cx = curr[0], cy = curr[1], cd = curr[2];

            if (cd > minVisitDist) break;

            if (persons[cx][cy] > 0) {
                minVisitDist = cd;
                pq.offer(new int[]{cx, cy, cd, persons[cx][cy]});
            }

            for (int i = 0; i < 4; i++) {
                int dx = cx + dir[i][0];
                int dy = cy + dir[i][1];

                if (dx < 1 || dx > n || dy < 1 || dy > n) continue;
                if (map[dx][dy] == 1 || visited[dx][dy]) continue;

                visited[dx][dy] = true;
                q.offer(new int[]{dx, dy, cd + 1});
            }
        }

        if (pq.isEmpty()) return new int[]{-1, -1, -1, -1};
        int[] best = pq.poll();
        return new int[]{best[3], best[2], best[0], best[1]}; // index, dist, r, c
    }

    public int bfs_arrive(int tx, int ty, int[][] map, int n) {
        Deque<int[]> dq = new ArrayDeque<>();
        int[][] dist = new int[n+1][n+1];

        dq.offer(new int[]{sx, sy});
        dist[sx][sy] = 0;

        while(!dq.isEmpty()) {
            int[] curr = dq.poll();
            int cx = curr[0];
            int cy = curr[1];

            if(cx == tx && cy == ty) return dist[cx][cy];

            for(int i=0;i<4;i++) {
                int dx = cx + dir[i][0];
                int dy = cy + dir[i][1];

                if (dx < 1 || dx > n || dy < 1 || dy > n) continue;
                if(map[dx][dy] == 1) continue;
                if(dist[dx][dy] > 0) continue;

                dq.offer(new int[]{dx,dy});
                dist[dx][dy] = dist[cx][cy] + 1;
            }
        }

        return -1;
    }

}