import kotlin.math.*
class Solution {
    fun solution(wallpaper: Array<String>): IntArray {
        var answer: MutableList<Int> = mutableListOf(100,100,0,0)
        //minX, minY, maxX, maxY
        //탐색을 진행하면서 x,y의 최대값 | 최소값 구하면 됨.
        
        for (i in 0..wallpaper.size-1) {
            for (j in 0..wallpaper[i].length-1) {
                val c = wallpaper[i][j]
                if(c == '#') {
                    answer[0] = min(answer[0], i)
                    answer[1] = min(answer[1], j)
                    answer[2] = max(answer[2], i+1)
                    answer[3] = max(answer[3], j+1)
                }
            }
        }
        return answer.toIntArray()
    }
}