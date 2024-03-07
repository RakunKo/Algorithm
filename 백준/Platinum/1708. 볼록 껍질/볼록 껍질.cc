#include <algorithm>
#include <iostream>
#include <vector>
#define MAX 100001

using namespace std;

struct coordinate {
  long long x, y;
};

coordinate pivot;
int n;
vector<coordinate> v;
vector<coordinate> s;



long long ccw(coordinate c1, coordinate c2, coordinate c3) {
  return c1.x * c2.y + c2.x * c3.y + c3.x * c1.y - (c2.x * c1.y + c3.x * c2.y + c1.x * c3.y);
}

bool cmp(coordinate c1, coordinate c2) {
  if (c1.y == c2.y)
    return c1.x < c2.x;
  else
    return c1.y < c2.y;
}

bool compare(coordinate c1, coordinate c2) { //반시계 정렬

  long long cc = ccw(pivot, c1, c2);

  if (cc) //각도가 작은 순
    return cc > 0;
  else if (c1.y != c2.y) // y가 작은 순
    return c1.y > c2.y;
  else // x가 작은 순
    return c1.x > c2.x;
}

int main() {
  cin >> n;
  for (int i = 0; i < n; i++) {
    long long x, y;
    cin >> x >> y;
    v.push_back({x, y});
  }

  sort(v.begin(), v.end(), cmp);     // y가 작은 순으로 정렬
  s.push_back({v[0].x, v[0].y});     //제일 작은 y 삽입
  pivot = {v[0].x, v[0].y};
  sort(v.begin(), v.end(), compare); //기준으로 각도 정렬
  v.pop_back();  //첫 점은 항상 첫 선택한 점
  s.push_back({v.back().x, v.back().y});
  v.pop_back();

  while (!v.empty()) {
    coordinate c = {v.back().x, v.back().y};
    v.pop_back();
    while (ccw(c, s[s.size() - 1], s[s.size() - 2]) <= 0 && s.size() >= 2) {
      s.pop_back();
    }
    s.push_back(c);
  }
  cout<<s.size();
  return 0;
}
