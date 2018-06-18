public class Solution {
    public boolean IsBalanced_Solution(TreeNode root) {
        /*
        第一种方法，分别计算左右子树的高度，差不超过1
        */
        if(root==null) return true;
        int l1=getLength(root.left);
        int l2=getLength(root.right);
        int diff=l1-l2;
        if(diff>1||diff<-1){
            return false;
        }
        //接下来不断递归调用IsBalanced_Solution求左右子树的高
        return IsBalanced_Solution(root.left)&&IsBalanced_Solution(root.right);
        
    }
    public int getLength(TreeNode root){
        if(root==null) return 0;
        int left=getLength(root.left);
        int right=getLength(root.right);
        return left>right?(left+1):(right+1);//递归，树的深度等于左右子树的最大深度+1
    }
}