// 思路：
// 中序遍历：左、根、右
// 方法1：递归
// 方法2：自己维护栈
// 方法3：Morris遍历


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

// 方法一：递归
// class Solution {
//     public List<Integer> inorderTraversal(TreeNode root) {
//         // 定义返回结果集
//         List<Integer> list = new ArrayList<Integer>();

//         // 中序遍历
//         inorder(root, list);

//         return list;
//     }

//     public void inorder (TreeNode root, List<Integer> list) {
//         if (root == null) {
//             return;
//         }

//         inorder(root.left, list);
//         list.add(root.val);
//         inorder(root.right, list);
//     }
// }

// 方法二：手动维护栈，模拟递归
// class Solution {
//     public List<Integer> inorderTraversal(TreeNode root) {
//         // 定义返回结果集
//         List<Integer> list = new ArrayList<Integer>();
//         // 定义栈
//         Deque<TreeNode> stack = new LinkedList<TreeNode>();

//         // 开始遍历，
//         while (root != null || !stack.isEmpty()) {
//             // 逐层遍历树的左节点入栈，直到左节点为null
//             while (root != null) {
//                 stack.push(root);
//                 root = root.left; 
//             }

//             // 栈顶元素出栈
//             root = stack.pop();
//             // 存值
//             list.add(root.val);

//             root = root.right;
//         }

//         return list;
//     }
// }


// 方法3：Morris遍历
// predecessor：前驱节点，为当前节点的左子树中最右边的节点

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        // 定义返回结果集
        List<Integer> list = new ArrayList<Integer>();
        // 定义前驱节点
        TreeNode predecessor = null;

        // 开始遍历，
        while (root != null) {
            // 当前节点root有左孩子，需要继续遍历左子树
            if (root.left != null) {
                // 设置predecessor
                // predecessor，就是当前root节点向左走一步，然后一直向右走，直到走不下去
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }

                // 当前节点root的predecessor的右孩子为空，并没有指向root本身
                // 说明此时正在逐层往下遍历左节点，因此，需设置prodecessor的右孩子指向当前root节点
                // 当第一次遍历到
                if (predecessor.right == null) {
                    predecessor.right = root;
                    // 继续遍历左子树
                    root = root.left;
                } else {
                    // 此时，predecessor的右孩子是指向当前root节点的，才会进入此逻辑
                    // 说明当前root节点的左子树已经遍历完了，可以把当前节点root的值输出
                    list.add(root.val);
                    // 还没想清楚，如果不置为null，有什么影响
                    predecessor.right = null;
                    // 开始遍历当前root节点的右子树
                    root = root.right;
                }

            } else {
                // 当前节点没有左孩子，已经是最左边的节点，应该输出
                list.add(root.val);
                // 左子树遍历完，需回到根节点，因为predecessor的操作，当前节点的右孩子是指向当前节点的父节点
                root = root.right;
            }
        }

        return list;
    }
}