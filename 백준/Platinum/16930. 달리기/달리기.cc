#include <iostream>
#include <algorithm>
#include <queue>
#define MAX 1003
#define INF 2e9
using namespace std;

char map[MAX][MAX];
int sec[MAX][MAX];
int dir[4][2] = {{1,0}, {-1,0}, {0,1}, {0, -1}};
int k;
int x1,x2;
int Y1,y2;
int m,n;

struct coor {
    int x;
    int y;
};

queue<coor> q;

int bfs(int x, int y) {
    q.push({x,y});
    sec[x][y] = 0;
    
    while(!q.empty()) {
        coor c = q.front();
        q.pop();

        if (c.x == x2 && c.y == y2) {
            return sec[x2][y2];
        }
        
        for (int i=0;i<4;i++) {
            for (int j=1;j<=k;j++) {
                int dx = c.x + dir[i][0]*j;
                int dy = c.y + dir[i][1]*j;

                if (dx > n || dx <= 0 || dy >m || dy <=0) break;
                if (map[dx][dy] == '#') break;
                if (sec[dx][dy] < sec[c.x][c.y] + 1) break; //기준점 보다 방문해야할 곳이 작으면 이미 최소거리 확정
                
                if (sec[dx][dy] == INF) {
                    sec[dx][dy] = sec[c.x][c.y] + 1;
                    q.push({dx, dy});
                }
            }
        }
        /*for (int i=1;i<=n;i++) {
            for(int j=1;j<=m;j++) cout<<sec[i][j];
            cout<<endl;
        }
        cout<<endl;*/
    }
    return -1;
}

int main() {
    cin>>n>>m>>k;
    for (int i=1;i<=n;i++) {
        for (int j=1;j<=m;j++) {
            cin>>map[i][j];
            sec[i][j] = INF;
        }
    }
    cin>>x1>>Y1>>x2>>y2;
    cout<<bfs(x1,Y1);
    return 0;
}

