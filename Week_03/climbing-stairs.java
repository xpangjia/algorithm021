// 记录：
// 方法一：动态规划
// 方法二：递归




// 方法一：动态规划
// 时间复杂度：O(n)
// 空间复杂度：O(1)
// class Solution {
//     public int climbStairs(int n) {
//         int left = 0, right = 0, result = 1;
//         // 从第一级台阶开始循环
//         for (int i = 1; i <= n; i++) {
//             left = right;
//             right = result;
//             result = left + right;
//         }
//         return result;
//     }
// }

// class Solution {
//     public int climbStairs(int n) {
//         int first = 1, second = 1;
//         // 从第二级台阶开始循环
//         for (int i = 2; i <= n; i++) {
//             // 计算当前台阶的方法
//             second = second + first;
//             // 计算前一级台阶的方法
//             first = second - first;
//         }
//         return second;
//     }
// }


// 方法二：递归
// 思路：每一级台阶的方法数，等于前两级之和
// 缺陷：没有优化，n太大会超时
// 时间复杂度：O(n)
// class Solution {
//     public int climbStairs(int n) {
//         if (n <= 1) {
//             return 1;
//         } 
//         if (n < 3) {
//             return n;
//         }
//         return climbStairs(n-1) + climbStairs(n -2);
//     }
// }

// 优化
class Solution {
    Map<Integer, Integer> map = new HashMap<>();
    public int climbStairs(int n) {
        if (n <= 1) {
            map.put(1, 1);
            return 1;
        } 
        if (n < 3) {
            map.put(n, n);
            return n;
        }
        if(map.get(n) != null) {
            return map.get(n);
        } else {
            int r = climbStairs(n-1) + climbStairs(n -2);
            map.put(n, r);
            return r;
        }
    }
}