public class Solution {
    /*
     * @param root: the root of binary tree
     * @return: the root of the minimum subtree
     */
    private int curSum = Integer.MAX_VALUE;
    private TreeNode sub = null;
    public TreeNode findSubtree(TreeNode root) {
        helper(root);
        return sub;
    }
    private  int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = root.val + helper(root.left) + helper(root.right);
        if (sum <= curSum) {
            curSum = sum;
            sub = root;
        }
        return sum;//返回当前子树的和
    }
}

public class Solution {
    /*
     * 采用完全分治的思想
     */
    private class resultType {
        public TreeNode sub;
        public int minSum;
        public int sum;
        public resultType(TreeNode sub, int minSum, int sum) { //如何定义构造函数
            this.sub = sub;
            this.minSum = minSum;
            this.sum = sum;
        }
    }

    public TreeNode findSubtree(TreeNode root) {
        resultType result = helper(root);
        return result.sub;
    }
    private resultType helper(TreeNode root) {
        if (root == null) {
            return new resultType(null, Integer.MAX_VALUE, 0);
        }
        resultType left = helper(root.left);
        resultType right = helper(root.right);
        resultType result = new resultType(
            root,
            root.val + left.sum + right.sum,
            root.val + left.sum + right.sum
        );
        if (left.minSum <= result.minSum) {
            result.sub = root.left; //不能直接判断此时的最小节点就是root.left
            //result.sub = left.sub;
            result.minSum = left.minSum;
        }
        if (right.minSum <= result.minSum) {
            result.sub = root.right;//不能直接就是root.right
            //result.sub = right.sub;
            result.minSum = right.minSum;
        }
        return result;
    }
}


public class Solution {
    /*
     * @param root: the root of binary tree
     * @return: the root of the maximum average of subtree
     */

    private class resultType{
        public TreeNode sub;
        public int sum, N;
        public int Ave, maxAve;
        public resultType(TreeNode sub, int N, int sum, int Ave, int maxAve) {
            this.sub = sub;
            this.N = N;
            this.sum = sum;
            this.Ave = Ave;
            this.maxAve = maxAve;
        }
    }

    public TreeNode findSubtree2(TreeNode root) {
        resultType result = helper(root);
        return result.sub;
    }

    private resultType helper(TreeNode root) {
        if (root == null) {
            return new resultType(null, 0, 0, 0, Integer.MIN_VALUE);
        }
        resultType left = helper(root.left);
        resultType right = helper(root.right);
        resultType result = new resultType(
            root,
            left.N + right.N + 1,
            left.sum + right.sum + root.val,
            (left.sum + right.sum + root.val)/(left.N + right.N + 1),
            (left.sum + right.sum + root.val)/(left.N + right.N + 1) 
        );
        if (result.maxAve <= left.maxAve) {
            result.maxAve = left.maxAve;
            result.sub = left.sub;
        }
        if (result.maxAve <= right.maxAve) {
            result.maxAve = right.maxAve;
            result.sub = right.sub;
        }
        return result;
    }
}