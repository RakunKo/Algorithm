#include <iostream>
#include <algorithm>
#include <queue>
#define MAX 101
#define INF 2e9
using namespace std;

int dir[4][2] = {{1,0},{-1,0},{0,1},{0,-1}};
int w,h, cx, cy, cnt[MAX][MAX];
char map[MAX][MAX];

struct coor {
    int x;
    int y;
};

queue<coor> q;

void bfs(int x, int y) {
    q.push({x,y});
    cnt[x][y] = -1;
    
    while (!q.empty()) {
        coor c = q.front();
        q.pop();
        
        if ((c.x != x || c.y != y)&&map[c.x][c.y] == 'C') {
            cout<<cnt[c.x][c.y];
            break; //찾음
        }
        
        for (int i=0;i<4;i++) {
            int dx = c.x;
            int dy = c.y;
            while (1) {
                dx += dir[i][0];
                dy += dir[i][1];
                
                if (dx >=h || dx < 0 || dy>=w || dy < 0) break;
                if (map[dx][dy] == '*') break;
                if (cnt[dx][dy] < cnt[c.x][c.y] + 1) break;
                
                if (cnt[dx][dy] == INF) {
                    q.push({dx,dy});
                    cnt[dx][dy] = cnt[c.x][c.y] + 1;
                }
            }
        }
        /*for (int i=0;i<h;i++) {
            for (int j=0;j<w;j++) cout<<cnt[i][j];
            cout<<endl;
        }
        cout<<endl;*/
    }
    return;
}

int main() {
    cin>>w>>h;
    for (int i=0;i<h;i++) {
        for (int j=0;j<w;j++) {
            cin>>map[i][j];
            cnt[i][j] = INF;
            if (map[i][j] == 'C') {
                cx = i;
                cy = j;
            }
        }
    }
    bfs(cx,cy);
    return 0;
}

