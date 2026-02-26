import java.util.*;
import java.lang.Math.*;

class Solution {
    class Node {
        int value, idx;
        Node left, right;
        public Node(int value, int idx) {
            this.value = value;
            this.idx = idx;
            this.left = null;
            this.right = null;
        }
    }
    
    Node[] nodes;
    int answer = 0;
    
    public int solution(int[] info, int[][] edges) {
        
        nodes = new Node[info.length];
        for(int i = 0; i < info.length; i++) nodes[i] = new Node(info[i] ,i);
        
        for(int[] edge: edges) {
            int parentIdx = edge[0];
            int childIdx = edge[1];

            Node parent = nodes[parentIdx];
            Node child = nodes[childIdx];

            if(parent.left == null) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }
        
        List<Integer> nextNodes = new ArrayList<>();
        nextNodes.add(0); 
        dfs(0, 0, 0, nextNodes);
        
        return answer;
    }
    
    public void dfs(int vertex, int wolf, int sheep, List<Integer> possible) {
        if(nodes[vertex].value == 0) sheep++;
        else wolf++;
        
        if(wolf >= sheep) return;
        answer = Math.max(answer, sheep);
        
        List<Integer> temp = new ArrayList<>(possible);
        temp.remove(Integer.valueOf(vertex));
            
        if(nodes[vertex].left != null) temp.add(nodes[vertex].left.idx);
        if(nodes[vertex].right != null) temp.add(nodes[vertex].right.idx);
        
        for(Integer n: temp) {
            dfs(nodes[n].idx, wolf, sheep, temp);
        }
    }
}