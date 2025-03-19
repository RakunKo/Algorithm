class Solution {
    var resgisterMemberSize = 0
    var salePrice = 0
    fun solution(users: Array<IntArray>, emoticons: IntArray): IntArray {
        var answer: IntArray = intArrayOf()
        
        //그럼 1~100명의 사용자 중에서 플러스 가입이 우선
        //플러스 가입이 같다면 목표액 최대일때
        //할인율은 10,20,30,40% 중 하나
        //그럼 이모티콘 당 경우의 수 4
        //4^7 최대 경우의 수
        
        //할인율에 따른 이모티콘 가격
        Combinations(emoticons, 0, mutableListOf(), users)
        
        answer = answer.plus(intArrayOf(resgisterMemberSize, salePrice))

        
        
        return answer
    }
    
    fun Combinations(emoticons: IntArray, 
                     index:Int, 
                     current: MutableList<Int>,
                     users: Array<IntArray>) {
        if(index == emoticons.size) {
            //처리 로직, 탐색 완료
            //current에는 이모티콘 1,2,...의 할인 비율이 들어있음
            var totalAmount = 0;
            var totalCount = 0;
            
            for (user in users) {
                val userSale = user[0]
                val userAmount = user[1]
                
                var amount = 0 //가격
                
                for (i in 0..current.size-1) {
                    if (userSale <= current[i]) { //할인율이 더 높다면 
                        amount += (emoticons[i] - emoticons[i]*current[i]/100)   //구매
                    }
                }
                
                if(amount >= userAmount) {   //플러스 구매 
                    totalCount++
                } else {
                    totalAmount += amount
                }
            }
            print(totalCount.toString() + " "+ totalAmount.toString()+"\n")
            if (totalCount > resgisterMemberSize) {  //플러스 구매 유저가 더 많음
                resgisterMemberSize = totalCount
                salePrice = totalAmount
            }else if (totalCount == resgisterMemberSize) {  //같음
                if(totalAmount > salePrice) salePrice = totalAmount //더 가격이 많으면
            }
            
            return;
        }
        
        for (i in 0..3) {
            current.add(10*(i+1))
            Combinations(emoticons, index+1, current, users)
            current.removeAt(current.size-1) //제거
        }
    }
}