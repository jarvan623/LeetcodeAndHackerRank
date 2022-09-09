import java.util.Arrays;

public class Q654 {
  /*You are given an integer array nums with no duplicates. A maximum binary tree can be built recursively from nums using the following algorithm:

  Create a root node whose value is the maximum value in nums.
  Recursively build the left subtree on the subarray prefix to the left of the maximum value.
  Recursively build the right subtree on the subarray suffix to the right of the maximum value.
  Return the maximum binary tree built from nums.
  https://leetcode.cn/problems/maximum-binary-tree*/
  public static void main(String[] args) {
    int[] nums = new int[]{3,2,1,6,0,5};
    System.out.println(TreeNode.toString(constructMaximumBinaryTree(nums)));
  }
    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        if(nums==null||nums.length==0){
            return null;
        }
        int[] temp = new int[2];
        for(int i = 0;i<nums.length;i++){
            if(nums[i]>temp[0]){
                temp[0]=nums[i];
                temp[1]=i;
            }
        }
        TreeNode root = new TreeNode(temp[0]);
        root.left = constructMaximumBinaryTree(Arrays.copyOfRange(nums,0,temp[1]));
        root.right = constructMaximumBinaryTree(Arrays.copyOfRange(nums,temp[1]+1,nums.length));
        return root;
    }
}
