class Solution {
    fun solution(today: String, terms: Array<String>, privacies: Array<String>): IntArray {
        var answer: IntArray = intArrayOf()
        
        //오늘 날짜 parse
        val todayParts = today.split('.')
        val todayYear = todayParts[0].toInt()
        val todayMonth = todayParts[1].toInt()
        val todayDay = todayParts[2].toInt()
        
        //약관 parse
        val termMap = mutableMapOf<String, Int>()
        
        for(term in terms) {
            val termParts = term.split(' ')
            val termCategory = termParts[0]
            val termExpire = termParts[1].toInt()
            termMap.put(termCategory, termExpire)
        }
        
        var index = 1
        for(privacie in privacies) {
            val privacieParts = privacie.split(' ')
            val privacieDate = privacieParts[0]
            val privacieCategory = privacieParts[1]
            
            val privacieDateParts = privacieDate.split('.')
            val privacieYear = privacieDateParts[0].toInt()
            val privacieMonth = privacieDateParts[1].toInt()
            val privacieDay = privacieDateParts[2].toInt()
            
            //유효기간 나옴
            val selectedTermExpire = termMap[privacieCategory]!!.toInt()
            
            var addYear = privacieYear
            var addMonth = privacieMonth + selectedTermExpire
            var addDay = privacieDay
            
            if (addMonth > 12) {     
                val month = addMonth % 12 //0~11
                val year = addMonth / 12        
                //13~24이면 year = 1
                //25~36면 year = 2
                //36이면 year = 3
                if(month == 0) {
                    addMonth = 12
                    addYear += (year-1)
                }
                else {
                    addMonth = month
                    addYear += year
                }
            }
            addDay -= 1
            if (addDay == 0) {
                addDay = 28
                addMonth -= 1
                if (addMonth == 0) {
                    addMonth = 12
                    addYear -= 1
                }
            }
            
            if (addYear < todayYear) { //넘치면 삭제해야함
                answer = answer.plus(index)

            } 
            else if (addYear == todayYear) {
                if (addMonth < todayMonth) {
                    answer = answer.plus(index)
                }
                else if (addMonth == todayMonth) {
                    if (addDay < todayDay) {
                        answer = answer.plus(index)
                    }
                }
            }
            index++
        }
        
        return answer
    }
}