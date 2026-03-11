import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        int n = info.length;
        
        Map<String, List<Integer>> map = new HashMap<>();
        for(int i=0;i<n;i++) {
            String[] part = info[i].split(" ");
            make(part, "", 0, map);
        }
        
        for(String key : map.keySet()) Collections.sort(map.get(key));
        
        int k = query.length;
        int[] answer = new int[k];
        for(int i=0;i<k;i++) {
            String[] part = query[i].split(" ");
            String key = part[0]+part[2]+part[4]+part[6];
            
            List<Integer> list = map.get(key);
            
            int score = Integer.parseInt(part[7]);
            answer[i] = binary(score, list);
        }
        
        return answer;
    }
    
    public int binary(int s, List<Integer> list) {
        if(list == null || list.isEmpty()) return 0;
        int start = 0; int end = list.size();
        
        while(start < end) {
            int mid = start+(end-start)/2;
            
            if(list.get(mid) >= s) end = mid;
            else start = mid+1;
        }
        
        return list.size()-start;
    }
    
    public void make(String[] info, String str, int cnt, Map<String, List<Integer>> map) {
        if (cnt == 4) { 
            if (!map.containsKey(str)) {
                map.put(str, new ArrayList<>());
            }
            map.get(str).add(Integer.parseInt(info[4])); 
            return;
        }

        make(info, str + info[cnt], cnt + 1, map);
        make(info, str + "-", cnt + 1, map);
    }
}