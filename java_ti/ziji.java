public class Solution {
    /*
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */


/*
给定一个可能具有重复数字的列表，返回其所有可能的子集
注意事项
子集中的每个元素都是非降序的
两个子集间的顺序是无关紧要的
解集中不能包含重复子集
样例
如果 S = [1,2,2]，一个可能的答案为：
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
    */ 
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> results=new ArrayList<>();
        if (nums == null) {
            return null;
        }
        if (nums.length == 0) {
            results.add(new ArrayList<>());
        }
        Arrays.sort(nums);
        helper(results, new ArrayList<Integer>(), 0, nums);
        return results;
    }
    private void helper(List<List<Integer>> results,List<Integer> sub,int startIndex,int [] nums) {
        results.add(new ArrayList<Integer>(sub));
        for (int i=startIndex; i < nums.length; i++) {
            if (i!=startIndex && nums[i]==nums[i-1]) {//将后续的元素如果和前一个元素相同，那么就不进行
                continue;
            }
            sub.add(nums[i]);
            helper(results, sub, i+1, nums);
            sub.remove(sub.size()-1);
        }
    }
}