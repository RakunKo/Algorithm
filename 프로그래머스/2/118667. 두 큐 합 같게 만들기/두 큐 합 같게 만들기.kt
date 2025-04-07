class Solution {
    fun solution(queue1: IntArray, queue2: IntArray): Int {
        val q1 :MutableList<Int> = queue1.toMutableList()
        val q2 :MutableList<Int> = queue2.toMutableList()
        var answer: Int = -2
        var top1 = 0
        var top2 = 0
        //두 큐의 전체 합/2가 각각 만들어야함.
        //항상 높은 곳에서 가져온다면? -> 그리디
        var index = 0
        var sum1:Long = 0L
        var sum2:Long = 0L
        for (index in 0..queue1.size-1) {
            sum1 += queue1[index]
            sum2 += queue2[index]
        }
        //각 큐의 합이 같아질 수 없는 경우
        //1. 전체합이 홀수인 경우
        //2. 진행중 둘 중 하나의 합이 0이 되는 경우
        //3. 계속 반복되는 경우 -> cnt > 큐2개의 size
        //결국 size*2내에 작업이 완료되어야함.
        if((sum1+sum2)%2 == 1L) return -1    //전체 합이 홀수면 양쪽이 같아질 수 없음
        val target: Long = (sum1+sum2) / 2L
        var cnt  = 0
        while (true) {
            if(sum1 == 0L || sum2 == 0L) { //둘중 하나가 0이되면 X,X,X... < Y 이므로 불가능
                answer = -1
                break
            }
            if(cnt >= queue1.size*3){ //결국 size*2내에 작업이 완료되어야함.
                answer = -1
                break
            }
            if (sum1 == sum2) {
                answer = cnt
                break
            }else if (sum1 < sum2) {
                val num = q2[top2++]
                q1.add(num)
                sum1 += num
                sum2 -= num
            }else {
                val num = q1[top1++]
                q2.add(num)
                sum1 -= num
                sum2 += num
            }
            cnt++
        }
        return answer
    }
}
