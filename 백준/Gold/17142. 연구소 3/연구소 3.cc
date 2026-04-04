#include <iostream>
#include <queue>
#define MAX 51
using namespace std;
int n, m, map[MAX][MAX], vis[10], dis[MAX][MAX], disCopy[MAX][MAX];
int dir[4][2] = { {1,0},{-1,0},{0,1},{0,-1} };
struct VIRUS {
	int x, y;
	bool active;
};
VIRUS virus[10];
int virusCnt = 0;
bool isPossible = false;
int res = 5000;
void cul_min() {
	int maxx = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (dis[i][j] > 0 && map[i][j] != 2) {
				maxx = max(maxx, dis[i][j]);
			}
		}
	}
	res = min(res, maxx);
	return;
}
bool isPos() {
	bool isp = true;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (dis[i][j] == 0) {
				if (map[i][j] != 2) isp = false;
			}
		}
	}
	return isp;
}
void bfsVirus() {
	queue<pair<int, int>> q;
	for (int i = 0; i < virusCnt; i++) {
		if (!virus[i].active) continue;
		vector<vector<int>> v(MAX, vector<int>(MAX, 0));
		q.push({ virus[i].x, virus[i].y });
		v[virus[i].x][virus[i].y] = 1;
		dis[virus[i].x][virus[i].y] = 0;
		while (!q.empty()) {
			int x = q.front().first;
			int y = q.front().second;
			q.pop();
			for (int j = 0; j < 4; j++) {
				int dx = x + dir[j][0];
				int dy = y + dir[j][1];
				if (dx >= n || dx<0 || dy>=n || dy < 0 || dis[dx][dy] == -1) continue;
				if (v[dx][dy]) continue;
				if (dis[dx][dy] > 0) dis[dx][dy] = min(dis[x][y] + 1, dis[dx][dy]);
				else dis[dx][dy] = dis[x][y] + 1;
				q.push({ dx,dy });
				v[dx][dy] = 1;
			}
		}
	}
	if (isPos()) {
		isPossible = true;
		cul_min();
	}
}
void dfsVirus(int d, int prve) {
	if (d == m) {
		copy(&disCopy[0][0], &disCopy[0][0] + MAX * MAX, &dis[0][0]);
		bfsVirus();
	}
	else {
		for (int i = 0; i < virusCnt; i++) {
			if (vis[i] || prve > i)continue;
			virus[i].active = true;
			vis[i] = 1;
			dfsVirus(d + 1, i);
			virus[i].active = false;
			vis[i] = 0;
		}
	}
}
int main() {
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> map[i][j];
			if (map[i][j] == 2) {
				virus[virusCnt].x = i;
				virus[virusCnt].y = j;
				virus[virusCnt++].active = false;
			}
			if (map[i][j] == 1) disCopy[i][j] = -1;
		}
	}
	dfsVirus(0, -1);
	if (!isPossible) cout << -1;
	else cout << res;
	return 0;
}