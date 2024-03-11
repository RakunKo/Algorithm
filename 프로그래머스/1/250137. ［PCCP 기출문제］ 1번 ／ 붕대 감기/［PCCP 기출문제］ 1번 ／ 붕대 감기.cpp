#include <iostream>
#include <string>
#include <vector>

using namespace std;

int solution(vector<int> bandage, int health, vector<vector<int>> attacks) {
    int answer = 0;
    int time = 0;
    int success = 0;
    int turn = 0;
    int healthMax = health;
    
    while(1) {
        time++;
        if (time > attacks[attacks.size()-1][0]) break;
        
        if(attacks[turn][0] == time) {  //공격턴
            success = 0;
            health -= attacks[turn][1];
            turn++;
        }
        else {  //회복 가능
            health += bandage[1];
            if (health > healthMax) health = healthMax;
            success += 1;
        }
        
        if (health <= 0)  break;
        if (turn > attacks.size()) break;
        
        if (success == bandage[0]) { //추가 회복 
            health += bandage[2];
            if (health > healthMax) health = healthMax;
            success = 0;
        }
    }
    
    if (health <= 0) answer = -1;
    else answer = health;
    
    
    return answer;
}