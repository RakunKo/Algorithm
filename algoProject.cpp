#include<iostream>
#include<algorithm>
#include<cstring>
#include<vector>
#include<queue>
#include<string>
using namespace std;
int H, N;
string map[200];
char MAP[200][200];		//진짜 맵
bool region[200]; 
int  dx[] = { 0,0,1,-1 }, dy[] = { 1,-1,0,0 };
bool iswe[200];
char Hex[16][4] = { {'0','0','0','0'},{'0','0','0','1'},
{'0','0','1','0'},{'0','0','1','1'},{'0','1','0','0'},{'0','1','0','1'},
{'0','1','1','0'},{'0','1','1','1'},{'1','0','0','0'},{'1','0','0','1'},
{'1','0','1','0'},{'1','0','1','1'},{'1','1','0','0'},{'1','1','0','1'},
{'1','1','1','0'},{'1','1','1','1'} };
int d[200][200];
void init() {
	memset(region, false, sizeof(region));
	memset(iswe, false, sizeof(iswe));
	memset(d, 0, sizeof(d));
}
void bfs(int x, int y, int cnt) {
	queue<pair<int, int> > q;
	char cval = MAP[x][y];
	q.push(make_pair(x, y ));
	d[x][y] = cnt;
	while (!q.empty()) {
		x = q.front().first; y = q.front().second;
		q.pop();
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i]; int ny = y + dy[i];
			if (nx < 0 || nx >= H || ny < 0 || ny >= 4 * N) {   //범위 내가 아니라면
				iswe[cnt] = true;		//배경이다...
				continue;
			}
			if (d[nx][ny] != 0) continue;
			if (MAP[nx][ny] != cval) continue;
			d[nx][ny] = cnt;	//빈공간 처리
			q.push(make_pair(nx, ny ));
		}
	}
}
int bfs2(int x, int y, int cnt) {
	int result = 0;
	char cval = MAP[x][y];
	queue<pair<int, int> > q;
	q.push(make_pair(x, y ));
	d[x][y] = cnt;
	while (!q.empty()) {
		x = q.front().first; y = q.front().second;
		q.pop();
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i]; int ny = y + dy[i];
			if (nx < 0 || nx >= H || ny < 0 || ny >= 4 * N) {  //범위 밖이면 탈락
				continue;
			}
			
			if (MAP[nx][ny] != cval) {	//다른 영역 발견
				if (region[d[nx][ny]] == false && iswe[d[nx][ny]] == false) {  //빈공간을 방문 했는지... + 범위를 초과하지 않는다면...
					region[d[nx][ny]] = true;
					result++;
				}
				continue;
			}
			if (d[nx][ny] != 0) continue;		//배경이나 빈공간 인것.
			d[nx][ny] = cnt;
			q.push(make_pair(nx, ny ));
		}
	}
	return result;
}
//문자안에 있는 것인가?
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	int t = 1;

	while (true) {
		cin >> H >> N;
		if (H == 0 && N == 0) return 0;		//끝
		for (int i = 0; i < H; i++) cin >> map[i];   //입력받기
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] - '0' >= 0 && map[i][j] - '0' <= 9) {  //0~9인코딩
					for (int k = 0; k < 4; k++) {
						MAP[i][k + 4 * j] = Hex[map[i][j] - '0'][k];
					}
				}
				if (map[i][j] - 'a' >= 0 && map[i][j] - 'a' <= 5) {  //a~f인코딩
					for (int k = 0; k < 4; k++) {
						MAP[i][k + 4 * j] = Hex[(map[i][j] - 'a' + 10)][k];
					}
				}
			}
		}  //인코딩

		int cnt = 1;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < 4 * N; j++) {
				if (d[i][j] == 0 && MAP[i][j] == '0') {   //빈공간이라면...
					//cout << i << ' ' << j << ' ' << d[i][j] << '\n';
					bfs(i, j, cnt);
					cnt += 1;
				}
			}
		}
		


		vector<char> ANS;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < 4 * N; j++) {
				if (d[i][j] == 0 && MAP[i][j] == '1') {		//문자일 경우
					int s = bfs2(i, j, cnt++);
					switch (s) {
					case 0:
						ANS.push_back('W');
						break;
					case 1:
						ANS.push_back('A');
						break;
					case 2:
						ANS.push_back('K');
						break;
					case 3:
						ANS.push_back('J');
						break;
					case 4:
						ANS.push_back('S');
						break;
					case 5:
						ANS.push_back('D');
						break;
					}

				}
			}
		}


		for (int i=0;i<H;i++) {
			for (int j=0;j<4*N;j++) {
				cout<<d[i][j];
			}
			cout<<endl;
		}
		sort(ANS.begin(), ANS.end());
		cout << "Case " << (t) << ": ";
		for (int i = 0; i < ANS.size(); i++) {
			cout << ANS[i];
		}
		cout << '\n';
		init();
		t++;
	}

}