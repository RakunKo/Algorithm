#include <string>
#include <vector>
#include <queue>
#include <cstring>
#include <map>
#include <set>

using namespace std;

vector<vector<int>> maps;
map<int, int> cnt;
int visited[501][501];
int n, m;
int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, 1, 0, -1};
int depth = 1;

void BFS(int sx, int sy){
    int tmp = 0;
    queue<pair<int, int>> q;
    q.push({sx, sy});
    maps[sx][sy] = depth;
    visited[sx][sy] = 1;
    
    while(!q.empty()){
        int x = q.front().first;
        int y = q.front().second;
        tmp++;
        q.pop();
        
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny] || maps[nx][ny] == 0) continue;
            maps[nx][ny] = depth;
            visited[nx][ny] = 1;
            q.push({nx, ny});
        }
    }
    
    cnt[depth++] = tmp; // depth번째 석유덩어리가 갖는 석유 총량
}

int solution(vector<vector<int>> land) {
    int answer = 0;
    n = land.size(); // 열의 길이
    m = land[0].size(); // 행의 길이
    memset(visited, 0, sizeof(visited)); // 방문 초기화
    
    maps = land;
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            if(visited[i][j] == 0 && maps[i][j] > 0) 
                BFS(i, j);
        }
    }
    
    for(int i=0; i<m; i++){
        int temp = 0;
        set<int> s; // set으로 어떤 석유덩어리를 얻었는지 확인
        for(int j=0; j<n; j++){
            s.insert(maps[j][i]);
        }
        for(auto it: s){
            temp += cnt[it];
        }
        answer = max(answer, temp);
    }
    return answer;
}