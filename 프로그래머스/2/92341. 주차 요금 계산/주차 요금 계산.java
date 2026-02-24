import java.util.*;
import java.lang.Math.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<Integer, Integer> min = new HashMap<>();
        Map<Integer, Integer> cars = new HashMap<>();
        
        for(String record: records) {
            String[] str = record.split(" ");
            String[] time = str[0].split(":");
            int minute = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
            int number = Integer.parseInt(str[1]);
            
            if(cars.containsKey(number)) {
                int count = minute - cars.get(number);
                min.put(number, min.getOrDefault(number, 0) + count);     
                cars.remove(number);
            }else cars.put(number, minute);     
        }
        
        for(Map.Entry<Integer, Integer> e: cars.entrySet()) {            
            int number = e.getKey();

            int count = (23*60+59) - cars.get(number);
            min.put(number, min.getOrDefault(number, 0) + count);   
        }
        
        //정산
        return min.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .mapToInt(i -> {
                if(i.getValue() <= fees[0]) {
                    //기본 요금
                    return fees[1];
                }else {
                    int additional = (int) Math.ceil((double)(i.getValue() - fees[0]) / fees[2]);
                    return fees[1] + additional*fees[3];
                }
            })
            .toArray();
    }
}