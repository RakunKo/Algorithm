#include <iostream>
#include <algorithm>
#include <queue>
#include <vector>
#include<string.h>
using namespace std;
int h, w;
int s[201][204];
int dir[4][2] = { {-1,0},{1,0},{0,1},{0, - 1} };
bool isEdge[204];
int vis[204]; //빈공간 idx
int v[201][204];
int empty_cnt = 0;
vector<char> res;
void hexToBin(char c, int height, int wd) {
	int n = (c >= '0' && c <= '9') ? c - '0' : c - 'A' + 10;

    for (int i = 3; i >= 0; i--) {
        s[height][wd * 4 + i] = n % 2;
        n /= 2;
    }
    return;
}
void flood_fill(int x, int y, int cnt) {
    queue<pair<int, int> > q;
    q.push(make_pair(x, y));
    s[x][y] = cnt; // 초기 위치에 cnt 설정

    while (!q.empty()) {
        int cx = q.front().first;
        int cy = q.front().second;
        q.pop();

        for (int i = 0; i < 4; i++) {
            int dx = cx + dir[i][0];
            int dy = cy + dir[i][1];

            if (dx < 0 || dx >= h || dy < 0 || dy >= 4 * w) {
                isEdge[cnt] = true;
                continue;
            }

            if (s[dx][dy] != 0) continue;

            s[dx][dy] = cnt; // 빈 공간 처리
            q.push(make_pair(dx, dy));
        }
    }
}

void find_empty_space(int x, int y) {
    queue<pair<int, int> > q;
    q.push(make_pair(x, y));
    int areaVal = 1;  //글자
    v[x][y] = 1;

    while (!q.empty()) {
        int cx = q.front().first;
        int cy = q.front().second;
        q.pop();

        for (int i = 0; i < 4; i++) {
            int dx = cx + dir[i][0];
            int dy = cy + dir[i][1];

            if (dx < 0 || dx >= h || dy < 0 || dy >= 4 * w) {  //범위 밖은 탈락
                continue;
            }

            if (s[dx][dy] != areaVal) {     //다른 영역 찾음
                if (vis[s[dx][dy]] == 0 && isEdge[s[dx][dy]] == false) {  //영역에 방문하지 않고, 배경이 아닌 경우에만
                    vis[s[dx][dy]] = 1;
                    empty_cnt++;
                }
                continue;
            }

            if (s[dx][dy] != 1 || v[dx][dy] == 1) continue;  //글자가 아니거나, 이미 방문한 경우
            v[dx][dy] = 1;
            q.push(make_pair(dx, dy));
        }
    }
}
void print(int cnt) {
	if (cnt == 0) res.push_back('W');
	else if (cnt == 1) res.push_back('A');
	else if (cnt == 2) res.push_back('K');
	else if (cnt == 3) res.push_back('J');
	else if (cnt == 4) res.push_back('S');
	else if (cnt == 5) res.push_back('D');
	return;
}
int main() {
    int page = 1;
	while (1) {
		cin >> h >> w;
		if (h == 0 && w == 0) break;  //종료
		for (int j = 0; j < h; j++) {
			for (int k = 0; k < w; k++) {
				char temp;
				cin >> temp;
				hexToBin(temp, j, k);
			}
		}   //인코딩
		int cnt = 2;
		for (int j = 0; j < h; j++) {
			for (int k = 0; k < 4 * w; k++) {
				if (s[j][k] == 0) {  
					flood_fill(j, k, cnt);  //배경 칠하기
					cnt++;
				}
			}
		}
        for (int i=0;i<h;i++) {
			for (int j=0;j<4*w;j++) {
				cout<<s[i][j];
			}
			cout<<endl;
		}
        //여기까진 맞음
		for (int j = 0; j < h; j++) {
			for (int k = 0; k < 4*w; k++) {
				if (s[j][k] == 1 && v[j][k]==0) {  //문자이면서 방문 안했다면
					empty_cnt = 0;
					find_empty_space(j, k);  
                    print(empty_cnt);
				}
			}
		}
        sort(res.begin(), res.end());
        cout << "Case " << (page) << ": ";
		for (int i = 0; i < res.size(); i++) {
			cout << res[i];
		}
		cout << '\n';
		memset(s, 0, sizeof(s));
		memset(vis, 0, sizeof(vis));
		memset(v, 0, sizeof(v));
		memset(isEdge, 0, sizeof(isEdge));
        res.clear();
		page++;
	}
	return 0;
}