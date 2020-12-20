// 记录：
// 方法一：递归


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


// 方法一：
// 思路：利用前序遍历和中序遍历的特点，找出根节点，及左右子树范围；
//         虽然有了思路，不过没想出怎么做，直接看题解了
//  前序遍历：[根节点, [左子树], [右子树]]
//  中序遍历：[[左子树], 根节点， [右子树]]
//  时间复杂度：O(n)
//  空间复杂度：O(n)
//      存储答案的O(n)空间，存储哈希表O(n)，递归时的栈空间O(h)，h<n
class Solution {
    // 哈希表，存储每一个节点，便于快速查询
    private Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 节点个数
        int n = preorder.length;

        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }

        return buildNode(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    // 通过递归，构建每一个节点
    private TreeNode buildNode(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        // terminator：
        if(preorder_left > preorder_right) {
            return null;
        }

        // process：构建TreeNode
        // 找出根节点，前序遍历的第一个节点就是
        int preorder_root = preorder_left;
        // 定位到中序遍历中，根节点的位置
        int inorder_root = indexMap.get(preorder[preorder_root]);

        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 左子树的节点数量
        int size_left = inorder_root - inorder_left;

        // drill down：通过递归，生成子节点
        
        // 要构造左子树的节点，所以，要以前序遍历的左子树结果，和中序遍历的左子树结果，作为新的初始数据源，以同样的逻辑递归，求出左子树的根节点
        //  前序遍历的左子树范围：[preorder_left + 1, preorder + size_left]
        //  中序遍历的左子树范围：[inorder_left, inorder_root - 1]
        root.left = buildNode(preorder, inorder, preorder_left + 1, preorder_left + size_left, inorder_left, inorder_root - 1);

        // 同理，构造右子树节点
        root.right = buildNode(preorder, inorder, preorder_left + size_left + 1, preorder_right, inorder_root + 1, inorder_right);

        return root;
    }

}