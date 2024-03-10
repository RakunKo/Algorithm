#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include <algorithm>

using namespace std;

vector<int> gift_score;
vector<vector<int>> graph;
    vector<int> gift ;

vector<string> split(string str, char Delimiter) {
    istringstream iss(str);           
    string buffer;                    
 
    vector<string> result;
 
    while (getline(iss, buffer, Delimiter)) {
        result.push_back(buffer);               
    }
 
    return result;
}

int solution(vector<string> friends, vector<string> gifts) {
    int answer = 0;
    gift_score=vector<int>(friends.size(), 0);
    graph=vector<vector<int>>(friends.size(),vector<int>(friends.size(),0));
    
    for (int i =0;i<gifts.size();i++) {
        vector<string> split_friends = split(gifts[i], ' ');
        
         for (int j=0;j<friends.size();j++) {
            for (int k=0;k<friends.size();k++) {
                if (friends[j].compare(split_friends[0]) == 0
                   && friends[k].compare(split_friends[1]) == 0) {
                    graph[j][k]++;
                }
            }
        }
        
        for (int j=0;j<friends.size();j++) {
            if (split_friends[0].compare(friends[j]) == 0) {
                gift_score[j]++;
            }//같으면 +, 준 사람
        }
        for (int j=0;j<friends.size();j++) {
            if (split_friends[1].compare(friends[j]) == 0) {
                gift_score[j]--;
            }//같으면 -, 받는 사람
        }
    }

    gift=vector<int>(friends.size(), 0);
    
    //여기 코드 수정
     for (int i=0;i<friends.size();i++) {
            for (int j=0;j<friends.size();j++) {
                if (i == j) continue; 
                if ((graph[i][j] == 0 && graph[j][i] == 0)) { //주고 받은거 없음
                    if (gift_score[i] == gift_score[j]) continue;
                    else if (gift_score[i] > gift_score[j]) gift[i]++;
                    else gift[j]++;
                } else if (graph[i][j] == graph[j][i]) {
                    if (gift_score[i] == gift_score[j]) continue;
                    else if (gift_score[i] > gift_score[j]) gift[i]++;
                    else gift[j]++;
                }
                else {
                    if (graph[i][j] > graph[j][i]) gift[i]++;
                    else gift[j]++;
                }
            }
        }
    
    answer = *max_element(gift.begin(), gift.end());
    return answer/2;
}