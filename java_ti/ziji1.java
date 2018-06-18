public class Solution {
    
    /*
     * @param nums: A set of numbers
     * @return: A list of lists
     */
    public List<List<Integer>> subsets(int[] nums) {
        //List和ArrayList的区别
        List<List<Integer>> results=new LinkedList<>();
        //ArrayList<ArrayList<Integer>>()不能转化为List,new ArrayList<>()
        if (nums == null) {
            return results;
        }
        //注意nums == null 和nums.length == 0的区别
        if (nums.length == 0) {
            results.add(new ArrayList<Integer>());
            return results;
        }
        Arrays.sort(nums);
        Queue<List<Integer>> queue=new LinkedList<>();
        //建立一个二维优先队列，在该优先队列中保存List，每次将List offer到里面，之后从优先队列中将list弹出,保存到results中
        
        queue.offer(new LinkedList<Integer>());//应为弹出的List要经常增删，用LinkedList
        
        //用BFS不用写递归
        while (!queue.isEmpty()) {
            List<Integer> subset = queue.poll();
            results.add(subset);
            //宽度优先搜索，每弹出一个值，该值有关的数值先有关的所有数加入到队列中。每一for循环加入多个数，每一次while循环弹出一个数
            //所以会出现一种情况，下一次弹出的值不满足条件，不进行for循环一直弹出队列。    
            for (int i = 0; i < nums.length; i++) {
                if (subset.size() == 0 || subset.get(subset.size() - 1) < nums[i]) {
                    List<Integer> nextSubset = new LinkedList<Integer>(subset);
                    nextSubset.add(nums[i]);
                    queue.offer(nextSubset);
                }
            }
        }
        return results;
    }
}