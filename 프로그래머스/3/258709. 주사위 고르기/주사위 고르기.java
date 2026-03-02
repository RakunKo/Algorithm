import java.util.*;

class Solution {
    int max = 0;
    int[] answer;
    
    public int[] solution(int[][] dice) {
        int n = dice.length;
        answer = new int[n/2];
        
        List<Integer> list = new ArrayList<>();
        select(0, 0, n, dice, list);
        
        return answer;
    }
    
    public void select(int s, int d, int n, int[][] dice, List<Integer> listA) {
        if(d == n/2) {
            //System.out.println(set);
            compare(n, dice, listA);
            return;
        }
        
        for(int i=s;i<n;i++) {
            listA.add(i);
            select(i+1, d+1, n, dice, listA);
            listA.remove(listA.size()-1);
        }
    }
    
    
    public void compare(int n, int[][] dice, List<Integer> listA) {
        List<Integer> listB = new ArrayList<>();
        boolean[] isA = new boolean[n];
        for (int idx : listA) isA[idx] = true;
        for (int i = 0; i < n; i++) if (!isA[i]) listB.add(i);
        
        List<Integer> sumA = getSum(listA, dice);
        List<Integer> sumB = getSum(listB, dice);
        
        Collections.sort(sumB);
        
        int win = 0;
        for(Integer a: sumA) {
            win += binarySearch(sumB, a);
        }
        
        if (win > max) {
            max = win;
            for (int i = 0; i < listA.size(); i++) {
                answer[i] = listA.get(i)+1;
            }
        }
    }
    
    public List<Integer> getSum(List<Integer> list, int[][] dice) {
        List<Integer> sum = new ArrayList<>();
        sum.add(0);
        
        for (int i : list) {
            List<Integer> nextSums = new ArrayList<>();
            for (int currentSum : sum) {
                for (int face : dice[i]) {
                    nextSums.add(currentSum + face);
                }
            }
            sum = nextSums;
        }
        
        return sum;
    }
    
    public int binarySearch(List<Integer> list, int target) {
        int left = 0;
        int right = list.size();
        
        while(left < right) {
            int mid = (left + right)/2;
            if (list.get(mid) < target) left = mid + 1;
            else right = mid;
        }
        
        return left;
    }
}