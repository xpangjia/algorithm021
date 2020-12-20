// 记录：
// 方法一：暴力
// 

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//  方法一：暴力

// class Solution {
//     Deque<TreeNode> stack = new LinkedList<TreeNode>();

//     public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//         boolean matchp = false;
//         boolean matchq = false;
//         TreeNode lastVisit = root;
//         Map<String, Integer> map = new HashMap<String, Integer>();
//         while (!stack.isEmpty() || root != null) {
//             while (root != null) {
//                 // 左子树遍历
//                 stack.push(root);
//                 root = root.left;
//             }

//             root = stack.peek();
            
//             if (root.right == null || root.right == lastVisit) {
//                 // 输出
//                 if (root.val == p.val) {
//                     matchp = true;
//                 }

//                 if (root.val == q.val) {
//                     matchq = true;
//                 }

//                 if (matchp == true) {
//                     map.put(root.val+"", map.getOrDefault(root.val+"", 0) + 1);
//                     if (map.get(root.val+"") == 2) {
//                         return root;
//                     }
//                 }

//                 if (matchq == true) {
//                     map.put(root.val+"", map.getOrDefault(root.val+"", 0) + 1);
//                     if (map.get(root.val+"") == 2) {
//                         return root;
//                     }
//                 }

//                 stack.pop();
//                 lastVisit = root;
//                 root = null;
//             } else {
//                 root = root.right;
//             }
//         }

//         return root;
//     }
// }


// 方法二：递归
// 时间复杂度：O(n)，所有节点遍历一次
// 空间复杂度：O(n)，递归调用的栈深度取决于二叉树的高度，二叉树最坏情况下为一条链，此时高度为N，所以空间复杂度为O(N)
// class Solution {
//     private TreeNode ans;

//     public Solution() {
//         this.ans = null;
//     }

//     private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
//         // terminator：节点为空，已无子节点
//         if (root == null) {
//             return false;
//         }

//         // drill down：层层递归
//         // 递归判断，当前节点的左子树是否包含p或q
//         boolean lson = dfs(root.left, p, q);
//         // 递归判断，当前节点的左子树是否包含p或q
//         boolean rson = dfs(root.right, p, q);

//         // process：找出公共祖先
//         // 当前节点是公共祖先的两种情形：
//         // 1、左右子树分别等于p、q；
//         // 2、节点等于p或q，且左或右子树包含p或q；
//         if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
//             ans = root;
//         }

//         // 返回当前节点的子树是否含有p或q的判断结果：
//         // 含有的情况：当前节点的子树含有p或q，或者当前节点的值是否等于p或q
//         return lson || rson || (root.val == p.val || root.val == q.val);
//     }

//     public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//         // 递归
//         this.dfs(root, p, q);
//         return this.ans;
//     }
// }



// 方法三：存储父节点
// 时间复杂度：O(n)
// 空间复杂度：O(n)
class Solution {

    Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
    Set<Integer> visited = new HashSet<Integer>();
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 层层递归，把每个节点的值，及其父节点，存储到map
        dfs(root);

        // 遍历p的父节点，存储到visited列表
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }

        // 遍历q的父节点，与已存储的visited列表进行比对，如果出现共同的父节点，则为最近公共祖先
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }

        return null;
    }

    private void dfs(TreeNode root) {
        // 递归左子树
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }

        // 递归右子树
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }

}
