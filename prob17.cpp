#define _USE_MATH_DEFINES
#include <iostream>
#define SIZE 10
using namespace std;
//prob17-Funny Chess

int n,k,s;
int broad[SIZE][SIZE];
int cnt = 0;
int dir[4][2] = {{1,1}, {1,-1}, {-1,1},{-1,-1}};

bool can_pos(int x, int y, int d) {
    while (1) { //범위 안
        x += dir[d][0];
        y += dir[d][1];
        if (x>=0 && y>=0 && x<n && y<n) {
            if (broad[x][y] == 1) {   //말이 존재한다
                return false;
            }
        }
        else break;
    }
    return true;
}

void find_pos(int k, int x, int y) {  //남은 말 갯수 = k
    if (k <= 0) {   //모든 말 배치
        cnt++;
        return;  
    }
    for (int i=0;i<n;i++) {
        for (int j=0;j<n;j++) {
            if (broad[i][j] != 1) {
                if ((x==i && j >= y) || (x < i)) {  //중복제거 (거꾸로 올라가는 거 방지)
                    if (can_pos(i,j,0) && can_pos(i,j,1) &&can_pos(i,j,2) &&can_pos(i,j,3)) {
                        broad[i][j]= 1;
                        find_pos(k-1, i , j);
                        broad[i][j]= 0;
                    }
                }
            }
        }
    }

}

int main() {
    cin>>s;
    for (int i=0;i<s;i++) {
        cin>>n>>k;
        find_pos(k, 0, 0);
        cout<<cnt<<endl;
        cnt = 0;
    }
}