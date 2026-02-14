import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] one = {1, 2, 3, 4, 5};
        int[] two = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] three = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        Map<Integer, Integer> m = new HashMap<>();
        m.put(1, 0); m.put(2, 0); m.put(3, 0);
        
        for(int i=0;i<answers.length;i++) {
            if(one[i % one.length] == answers[i]) m.put(1, m.get(1) + 1);
            if(two[i % two.length] == answers[i]) m.put(2, m.get(2) + 1);
            if(three[i % three.length] == answers[i]) m.put(3, m.get(3) + 1);
        }
        
        List<Integer> valueList = new ArrayList<>(m.values());
        int maxValue = Collections.max(valueList);
        
        List<Integer> answer = new ArrayList<>();
        m.forEach((k,v) -> {
            if(v == maxValue) answer.add(k);
        });
        
        return answer.stream()
            .mapToInt(i -> i)
            .toArray();
    }
}