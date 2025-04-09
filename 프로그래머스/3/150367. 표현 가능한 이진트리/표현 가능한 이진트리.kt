class Solution {
    fun solution(numbers: LongArray): MutableList<Int> {
        var answer: MutableList<Int> = mutableListOf()
        //포화 이진 트리의 원소 개수 -> 무조건 홀수
        //
        //7 : 111 -> 가능
        //42 : 101010 -> 0101010 있어야함
        //5 : 101 -> 101 있어야함
        //21 : 10101
        
        //58 0111010 
        //결국 추가할 수 있는 더미노드(이미 0인건 제외)는 제일 앞에만 가능하다.
        //1의 개수를 n이라고 하면 2n-1로 맞춰야함
        //추가로, 반드시 홀수길이를 맞춘다.
        //부모노드는 반드시 1이여야함.
        //95 1011111 or
        // 86 010101100
        // 128
        
        for(num in numbers) {
            //자릿수
            var binary = num.toString(2)
            binary = padToFullBinaryTree(binary)
            val flag = checkSubtree(binary, 0, binary.length - 1)
            if(flag) answer.add(1)
            else answer.add(0)
        }
        return answer
    }
    fun padToFullBinaryTree(bin: String): String {
        var length = 1
        while (length < bin.length) {
            length = length * 2 + 1
        }
        return bin.padStart(length, '0')
    }
    fun checkSubtree(tree: String, start: Int, end: Int): Boolean {
        if (start > end) return true

        val mid = (start + end) / 2
        val root = tree[mid]

        val leftValid = checkSubtree(tree, start, mid - 1)
        val rightValid = checkSubtree(tree, mid + 1, end)

        if (!leftValid || !rightValid) return false

        // 자식 중에 1이 있으면 부모(root)는 1이어야 함
        val hasOneChild = (start <= mid - 1 && tree.substring(start, mid).contains('1')) ||
                          (mid + 1 <= end && tree.substring(mid + 1, end + 1).contains('1'))

        return !(hasOneChild && root == '0')
    }
}
