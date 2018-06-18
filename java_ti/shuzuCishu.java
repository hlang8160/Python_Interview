import java.util.*;
/*
使用游侠队列来保存从大到小的值，最大值在队列头部
*/

public class Solution {
    public ArrayList<Integer> maxInWindows(int [] num, int k)
    {
        ArrayList<Integer> res=new ArrayList<Integer>();
        if(num==null||num.length<=0) return res;
        PriorityQueue<Integer> queue=new PriorityQueue<Integer>(Collections.reverseOrder());
        for(int i=0;i<num.length;i++){
            queue.offer(num[i]);
            if(i>=k) queue.remove(num[i-k]);
            if(i+1>=k) res.add(queue.peek());
        }
        return res;
    }
}