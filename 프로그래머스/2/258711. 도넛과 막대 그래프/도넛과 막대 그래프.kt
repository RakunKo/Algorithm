import kotlin.math.*
class Solution {
    var cnt = 0
    var answer: MutableList<Int> =  MutableList(4) {0}
    var inV: MutableList<Int> = MutableList(1000001) { 0 }
    var outV: MutableList<Int> = MutableList(1000001) { 0 }
    
    fun solution(edges: Array<IntArray>): MutableList<Int> {
        var maxV = 0
        for(edge in edges) {
            inV[edge[1]] += 1
            outV[edge[0]] += 1
            maxV = max(max(maxV, edge[0]), edge[1])
        }
        for(i in 1..maxV) {
            if(inV[i] == 0 && outV[i] >= 2) answer[0] = i
            else if (inV[i] >= 1 && outV[i] == 0) answer[2] += 1
            else if (inV[i] >=2 && outV[i] == 2) answer[3] += 1
        }
        answer[1] = outV[answer[0]] - answer[2] - answer[3]
        return answer
    }
}