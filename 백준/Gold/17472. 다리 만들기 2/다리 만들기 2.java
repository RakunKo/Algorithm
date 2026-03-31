import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] part = br.readLine().split(" ");

        int n = Integer.parseInt(part[0]);
        int m = Integer.parseInt(part[1]);

        int[][] map = new int[n][m];
        for(int i=0;i<n;i++) {
            String[] p = br.readLine().split(" ");
            for(int j=0;j<m;j++) map[i][j] = Integer.parseInt(p[j]);
        }

        Main main = new Main();
        int result = main.solution(n, m, map);

        System.out.print(result);
    }

    //1 ≤ N, M ≤ 10
    //3 ≤ N×M ≤ 100
    //2 ≤ 섬의 개수 ≤ 6

    int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};

    class Pos {
        int x; int y; int dir;
        public Pos(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    int answer = Integer.MAX_VALUE;

    public int solution(int n, int m, int[][] map) {
        int cnt = numbering(n,m,map);

        //for(int i=0;i<n;i++) {
        //    for (int j = 0; j < m; j++)
        //        System.out.print(map[i][j] + " ");
        //    System.out.println();
        //}

        // 결국 최소가 되려면 특정 n -> m으로 가는 다리는 하나여야함.
        // 최소가 되려면 n개의 섬 -> n-1개의 다리

        int[][] bridge = new int[cnt+1][cnt+1];

        for(int i=0;i<cnt+1;i++)
            for (int j = 0; j < cnt+1; j++)
                bridge[i][j] = Integer.MAX_VALUE;


        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(map[i][j] == 0) continue;
                connect_bridge(i, j, map[i][j], bridge, n, m, map);
            }
        }

        //for(int i=1;i<cnt;i++) {
        //    for (int j = 1; j < cnt; j++)
        //        System.out.print(bridge[i][j] + " ");
        //    System.out.println();
        //}

        answer = count_cost(1, cnt-1, bridge);

        if(answer == Integer.MAX_VALUE) return -1;
        else return answer;
    }

    public int numbering(int n, int m, int[][] map) {
        int[][] new_map = new int[n][m];
        Deque<Pos> dq = new ArrayDeque<>();
        int cnt = 1;

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(map[i][j] == 0 || new_map[i][j] != 0) continue;

                dq.offer(new Pos(i,j, 0));
                new_map[i][j] = cnt;

                while(!dq.isEmpty()) {
                    Pos curr = dq.poll();

                    for(int k=0;k<4;k++) {
                        int dx = curr.x + dir[k][0];
                        int dy = curr.y + dir[k][1];

                        if(dx >= n || dx < 0 || dy >= m || dy < 0) continue;
                        if(new_map[dx][dy] != 0 || map[dx][dy] == 0) continue;

                        dq.offer(new Pos(dx,dy,0));
                        new_map[dx][dy] = cnt;
                    }
                }

                cnt++;
            }
        }

        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                map[i][j] = new_map[i][j];

        return cnt;
    }

    public void connect_bridge(int x, int y, int index, int[][] bridge, int n, int m, int[][] map) {
        for(int i=0;i<4;i++) {
            int dx = x; int dy = y;
            int len = 0;

            while(true) {
                dx += dir[i][0];
                dy += dir[i][1];

                if(dx >= n || dx < 0 || dy >= m || dy < 0) break;
                if(map[dx][dy] == index) break;

                if(map[dx][dy] == 0) len++;
                else {
                    if(len >= 2) {
                        int end_index = map[dx][dy];
                        bridge[index][end_index] = Math.min(bridge[index][end_index], len);
                        bridge[end_index][index] = Math.min(bridge[end_index][index], len);
                    }
                    break;
                }
            }
        }
    }

    public int count_cost(int start, int cnt, int[][] bridge) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        boolean[] vis = new boolean[cnt+1];

        pq.offer(new int[]{start, 0});
        int totalCost = 0;
        int connectedCount = 0;

        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int cost = curr[1];

            if(vis[node]) continue;

            vis[node] = true;
            totalCost += cost;
            connectedCount++;

            for(int i=1;i<=cnt;i++) {
                if(vis[i]) continue;
                if(bridge[node][i] == Integer.MAX_VALUE) continue;

                pq.offer(new int[]{i, bridge[node][i]});
            }
        }

        if(connectedCount != cnt) return Integer.MAX_VALUE;
        return totalCost;
    }

}