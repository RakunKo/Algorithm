class Solution {
    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
        var answer: Long = 0
        //제일 높은 index의 집을 들렀다가 결국엔 물류창고로 돌아와야함.
        //1번 집까지 배달, 수거
        //그럼 가장 멀리 있는 것 부터 해야하지 않을까
        //제일 멀리 있는 것부터 배달, 돌아오면서 수거
        //마지막부터 search 하면서 배달, 돌아오면서 마지막부터 수거 진행
        //모두 0이 될때까지 반복하기
        var deliveryIndex = n - 1 // 마지막 배달해야 할 위치
        var pickupIndex = n - 1 // 마지막 수거해야 할 위치
    
        while (deliveryIndex >= 0 || pickupIndex >= 0) {
            //print("시작\n")
            var cnt = cap
            var pickupCnt = cap
            var maxDistance = 0
            
            // 배달할 위치 찾기
            while (deliveryIndex >= 0 && deliveries[deliveryIndex] == 0) {
                deliveryIndex--
            }
            
            // 수거할 위치 찾기
            while (pickupIndex >= 0 && pickups[pickupIndex] == 0) {
                pickupIndex--
            }
            
            maxDistance = maxOf(deliveryIndex, pickupIndex) + 1
            
            // 배달 수행
            for (i in deliveryIndex downTo 0) {
                if (cnt <= 0) break
                if (deliveries[i] > 0) {
                    val delivered = minOf(cnt, deliveries[i])
                    deliveries[i] -= delivered
                    cnt -= delivered
                }
            }
            
            // 수거 수행
            for (i in pickupIndex downTo 0) {
                if (pickupCnt <= 0) break
                if (pickups[i] > 0) {
                    val pickedUp = minOf(pickupCnt, pickups[i])
                    pickups[i] -= pickedUp
                    pickupCnt -= pickedUp
                }
            }
            
            // 이동 거리 추가
            answer += maxDistance * 2L
        }
        return answer
    }
}