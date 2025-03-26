import kotlin.math.abs
class Solution {
    val moves = mapOf(
        'd' to Pair(1, 0),
        'l' to Pair(0, -1),  // 왼쪽 이동
        'r' to Pair(0, 1),   // 오른쪽 이동
        'u' to Pair(-1, 0)  // 위쪽 이동
    )
    
    var str:String = "zzzzzzzzzzz"
    
    var isEnd = false
    
    
    fun distance(x: Int, y: Int, r: Int, c: Int) :Int {
        return abs(x-r) + abs(y-c)
    }
    
    fun solution(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
        var answer: String = ""
        //map 변환
        var map = Array(n) {CharArray(m) {'.'}}
        map[x-1][y-1] = 'S'
        map[r-1][c-1] = 'E'
        
        dfs(map, x-1,y-1,r-1,c-1,k, 0, "")
        
        if(str == "zzzzzzzzzzz") answer = "impossible"
        else answer = str
        //for(i in 0..map.size-1) {
        //   println(map[i].joinToString(" ")) 
        //}
        return answer
    }
    
    fun dfs(map : Array<CharArray>, x: Int, y: Int, r: Int, c: Int, k: Int, depth:Int, result:String) { //x,y는 현재위치
        if(isEnd) return
        if(distance(x,y,r,c) > k - depth) return    //남은거리로는 절대 도착 불가능
        //distance -> 남은거리, k-depth -> 이동 가능 거리
        //남은거리 <= 이동가능 거리
        if (distance(x, y, r, c) % 2 != (k - depth) % 2) return
        
        if (depth == k) {
            if (map[x][y] == 'E') {  
                //결국 d l r u 순으로 탐색하기 때문에 처음나온 값이 정답일 수 밖에 없음
                str = result
                isEnd = true
            }
            return
        }
        
        for ((command, move) in moves) {  // command는 'l', 'r', 'u', 'd'
            val (dx, dy) = move
            val cx = x + dx
            val cy = y + dy
            if ((cx < 0 || cy < 0)||(cx >= map.size || cy >= map[0].size)) continue //범위 벗어남
            dfs(map, cx, cy, r, c, k, depth + 1, result + command)
        }
        return
    }
}