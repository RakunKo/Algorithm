#include <iostream>
#include <queue>
#define MAX 11
using namespace std;
int n, m, k, a[MAX][MAX], map[MAX][MAX];
int dir[8][2] = { {1,0},{1,-1}, {1,1}, {-1,0}, {-1, 1}, {-1, -1},{0,1}, {0, -1} };
vector<int> t[MAX][MAX];
vector<int> dead[MAX][MAX];
int treeCnt() {
	int cnt = 0;
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			cnt += t[i][j].size();
		}
	}
	return cnt;
}
void winter() {
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			map[i][j] += a[i][j];
		}
	}
	return;
}
void fall() {
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			for (int k = 0; k < t[i][j].size(); k++) {
				if (t[i][j][k] % 5 == 0) {
					for (int h = 0; h < 8; h++) {
						int x = i + dir[h][0];
						int y = j + dir[h][1];
						if (x > n || x < 1 || y > n || y < 1) continue;
						t[x][y].push_back(1);
					}
				}
			}
		}
	}
	return;
}
void summer() {
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			if (dead[i][j].size() > 0) {
				while (!dead[i][j].empty()) {
					int k = dead[i][j].back() / 2;
					dead[i][j].pop_back();
					map[i][j] += k;
				}
			}
		}
	}
	return;
}
void spring() {
	priority_queue<int, vector<int>, greater<int>> q;
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			if (t[i][j].size() > 0) {
				while (!t[i][j].empty()) {
					int k = t[i][j].back();
					t[i][j].pop_back();
					q.push(k);
				}
				while (!q.empty()) {
					int k = q.top();
					q.pop();
					if (map[i][j] >= k) {
						map[i][j] -= k;
						t[i][j].push_back(k + 1);
					}
					else dead[i][j].push_back(k);
				}
			}
		}
	}
	return;
}
int main() {
	cin >> n >> m >> k;
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			cin >> a[i][j];
			map[i][j] = 5;
		}
	}
	for (int i = 0; i < m; i++) {
		int x, y, z;
		cin >> x >> y >> z;
		t[x][y].push_back(z);
	}
	while (k > 0) {
		spring();
		summer();
		fall();
		winter();
		k--;
	}
	cout << treeCnt();
	return 0;
}
