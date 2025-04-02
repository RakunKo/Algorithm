import kotlin.math.*
class Solution {
    //지표씩 비교는 알파벳 순
    val map: MutableMap<Char, Int> = mutableMapOf(
        Pair('R', 0),Pair('T', 0),Pair('C', 0),Pair('F', 0),
        Pair('J', 0),Pair('M', 0),Pair('A', 0),Pair('N', 0))
    
    fun solution(survey: Array<String>, choices: IntArray): String {
        var answer: String = ""
        for (i in 0..survey.size -1) {
            val first = survey[i][0]
            val second = survey[i][1]
            
            val score = choices[i] - 4
            if(score == 0) {   //아무것도 하지 않음
                
            }else if (score > 0) { //두번째
                map.computeIfPresent(second) { _, oldValue -> oldValue+ abs(score)}
            }else {     //첫번째
                map.computeIfPresent(first) { _, oldValue -> oldValue + abs(score) }
            }    
        }
        var index = 0 
        val keyList = map.keys.toList()
        val valueList = map.values.toList()
        while(index < 8) {
            if(valueList[index] >= valueList[index + 1]) {
                answer += keyList[index]
            }else if (valueList[index] < valueList[index + 1]) {
                answer += keyList[index + 1]
            }
            index = index + 2
        }
        return answer
    }
}