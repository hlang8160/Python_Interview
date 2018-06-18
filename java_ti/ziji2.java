public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null) {
            return results;
        }
        if (nums.length == 0) {
            results.add(new ArrayList<Integer>());
            return results;
        }

        Arrays.sort(nums);

        //BFS
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.offer(new LinkedList<Integer>());

        while (!queue.isEmpty()) {
            List<Integer> subset = queue.poll();
            results.add(subset);

            for (int i = 0; i < nums.length; i++) {
                if(subset.size() == 0 || subset.get(subset.size()-1) < nums[i]) {
                    List<Integer> nextSub = new LinkedList<Integer>(subset);
                    nextSub.add(nums[i]);
                    queue.offer(nextSub);
                }
            }
        }
    }
}