// 记录：方法一的单调栈是看了题解才做出来的。在看题解之前，有过两个思路，不过因为在外面，只能简单写了伪代码，还没法验证。
// 1、第一个思路跟方法一比较接近，不过我的思路解法应该要繁琐点，后续再抽时间验证；
// 2、第二个思路，在题解里也看到了，就是积水面积从垂直方向一层一层往上计算，也是有缕清思路再验证。

// 方法一：利用单调栈
class Solution {
    public int trap(int[] height) {
        int area = 0, cur = 0;

        // 设置栈，用来存储柱子的下标，当需要柱子做左右边界，再取出
        Deque<Integer> stack = new LinkedList<Integer>();

        // 从左到右，依次循环柱子
        while (cur < height.length) {
            // 当前柱子比栈顶柱子低时，会入栈，所以，入栈后的柱子高度，是单调递减的

            // 当栈里不为空，且当前柱子比栈顶的柱子高时，则会形成低洼可以储存雨水，需要进入循环，计算雨水的面积
            while (!stack.isEmpty() && height[cur] > height[stack.peek()]) {
                // 取出栈顶的柱子
                int top = stack.pop();
                // 取出栈顶的柱子后，如果栈空了，说明只剩一根柱子，自然无法储存雨水，跳过本次循环
                if (stack.isEmpty()) {
                    break;
                }
                // 积水的右边界，是当前柱子(cur)；积水的左边界，是现在栈顶的柱子(peek)；之前pop出的栈顶柱子，则代表了积水的位置
                // 开始计算，积水位置能储存多少单位的雨水
                // 计算积水之间的水平宽度：右柱子减去左柱子减1
                int distance = cur - stack.peek() - 1;
                // 计算积水的高度，积水是从最低的柱子（左边界）开始存水，高度只能到最高的柱子（右边界）的高度
                int area_height = Math.min(height[cur], height[stack.peek()]) - height[top];
                // 计算面积，积水面积是从垂直面，一层一层叠加起来的
                area += distance * area_height;
            }

            // 比栈顶高度小，会继续形成低洼，可以入栈
            stack.push(cur++);
        }

        return area;
    }
}