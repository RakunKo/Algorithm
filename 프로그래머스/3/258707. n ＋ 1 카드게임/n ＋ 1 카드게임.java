import java.util.*;
import java.lang.Math.*;

class Solution {
    boolean[] vis;
    
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        
        Set<Integer> hand = new HashSet<>();
        Set<Integer> possible = new HashSet<>();
        for(int i=0;i<n/3;i++) hand.add(cards[i]);
        int idx = n/3;
        
        int round = 1;
        vis = new boolean[n+1];
        while(idx < n) {
            possible.add(cards[idx++]);
            possible.add(cards[idx++]);
            
            boolean next = false;
            if(canGoNext(hand, hand, n)) next = true;
            else if(coin >= 1 && canGoNext(hand, possible, n)) {
                coin -= 1;
                next = true;
            }
            else if(coin >= 2 && canGoNext(possible, possible, n)) {
                coin -= 2;
                next = true;
            }
            
            if(!next) break;
            round++;
        }
        
        return round;
    }
    
    public boolean canGoNext(Set<Integer> s1, Set<Integer> s2, int n) {
        for(Integer num: s1) {
            if(vis[num]) continue;
            if(s2.contains(n+1-num)) {
                vis[num] = true; vis[n+1-num] = true;
                return true;
            }
        }
        return false;
    } 
}