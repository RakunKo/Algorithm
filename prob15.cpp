#define _USE_MATH_DEFINES
#include <iostream>
#include <algorithm>
#include <vector>
#include <stack>
#include <math.h>
#define SIZE 10
using namespace std;

struct point {
    double x, y;
};
vector<int> v;
point p[10000];
int n;
bool cmp_y(point a, point b) {
	if (a.y != b.y)	return a.y < b.y;
	else return a.x < b.x;
}
int main() {
    cin>>n;
    for (int i=0;i<n;i++) {
        double x,y;
        cin>>p[i].x>>p[i].y;
    }
    sort(p, p+n, cmp_y);
    v.push_back(0);  //초기값
    while(1) {
        int i = v.back();
        int minIdx = 0;
        double minAngle =1000;
        for (int j=0;j<n;j++) {
            if (i == j) continue;
            double tan = atan2(p[j].y-p[i].y, p[j].x - p[i].y);
            if (tan < 0) tan += 2*M_PI;
            if (tan < minAngle){
                minIdx = j;
                minAngle = tan;
            }
        }
        for (int j =0;j<v.size();j++) {
            if (v[j] == minIdx) {
                v.push_back(minIdx);
                break; //이미 존재
            }
        }
        v.push_back(minIdx);
    }
    double sum = 0;
    for (int i=0;i<v.size()-1;i++) {
        sum += sqrt(pow(p[v[i]].x - p[v[i+1]].x, 2)+ pow(p[v[i]].y - p[v[i+1]].y, 2));
    }
    cout << fixed;
    cout.precision(2);
    cout<<sum;
 
 }