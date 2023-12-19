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

int n;
point p[10000];
bool cmp_x(point a, point b) {
    return a.x < b.x;
}

double distance(point a, point b) {  //uclid distance
    return sqrt(pow(a.x-b.x, 2) + pow(a.y-b.y,2));
}
double cloestPair(point po[], int size) {
    sort(po,po+size,cmp_x);   //sort by x
    if (size <= 3) {
        double d = 1000000;
        for (int i=0;i<size;i++) {
            if (i == size-1) d = min(d, distance(po[i],po[0]));
            else d = min(d, distance(po[i],po[i+1]));
        }
        return d;
    }
    int m = size/2;
    point s1[10000],s2[10000];
    for (int i=0;i<size;i++) {   //array divide
        if (i<m) s1[i] = po[i];
        else s2[i-m] = po[i];
    }
    double minDistance = min(cloestPair(s1, m), cloestPair(s2, size-m));
    //set 2 * minDistance    //여기 검토
    double minD2 = 100000;
    double midian = p[m].x;
    for (int i=0;i<m;i++) { //s1
        if (s1[i].x >= midian-minDistance && s1[i].x <= midian-minDistance) {  //in 2 * minDistance
            for (int j=0;j<size-m;j++) {
                if (s2[i].x >= midian-minDistance && s2[i].x <= midian-minDistance) {  //in 2 * minDistance
                    double temp = distance(s1[i], s2[j]);
                    minD2 = min(temp, minDistance);
                 }   
            }
        }
    }
    minDistance =  min(minD2, minDistance);
    return minDistance;
}
int main() {
    cin>>n;
    for (int i=0;i<n;i++) {
        double x, y;
        cin>>p[i].x>>p[i].y;
    }
    printf("%.2f", cloestPair(p,n));
}