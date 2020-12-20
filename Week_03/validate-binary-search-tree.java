/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

//  方法一：递归
// class Solution {
//     public boolean isValidBST(TreeNode root) {
//         return helper(root, null, null);
//     }

//     public boolean helper(TreeNode node, Integer lower, Integer upper) {
//         // terminator
//         if (node == null) {
//             return true;
//         }

//         // process
//         // 搜索二叉树特点，左子树的值小于根节点，右子树的值大于根节点
//         // 所以，设定一个区间，（left， right）
//         int val = node.val;

//         if (lower != null && val <= lower) {
//             return false;
//         }
//         if (upper != null && val >= upper) {
//             return false;
//         } 

//         // 判断右子树，则下限是当前节点值
//         if (!helper(node.right, val, upper)) {
//             return false;
//         }
//         // 判断左子树，则上限是当前节点值
//         if(!helper(node.left, lower, val)) {
//             return false;
//         }

//         return true;
//     }
// }


// 方法二：中序遍历
// 思路：
//  1、中序遍历的顺序：左子树，根节点，右子树；
//  2、利用二叉搜索树的特点，中序遍历出来的值一定是递增的
class Solution {
    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                // 左子树遍历
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();

            // 如果中序遍历得到的值小于等于前一个inorder，则说明不是二叉搜索树
            if(root.val <= inorder) {
                return false;
            }

            inorder = root.val;
            root = root.right;
        }

        return true;
    }
}
