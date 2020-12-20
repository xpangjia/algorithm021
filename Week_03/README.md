# 每周总结

## 练习
- [爬楼梯](./climbing-stairs.java)（阿里巴巴、腾讯、字节跳动在半年内面试常考）
- [括号生成](./generate-parentheses.java) (字节跳动在半年内面试中考过)
- [翻转二叉树] (谷歌、字节跳动、Facebook 在半年内面试中考过)
- [验证二叉搜索树](./validate-binary-search-tree.java)（亚马逊、微软、Facebook 在半年内面试中考过）
- [二叉树的最大深度]（亚马逊、微软、字节跳动在半年内面试中考过）
- [二叉树的最小深度]（Facebook、字节跳动、谷歌在半年内面试中考过）
- [二叉树的序列化与反序列化]（Facebook、亚马逊在半年内面试常考）
- [Pow(x, n)] （Facebook 在半年内面试常考）
- [子集]（Facebook、字节跳动、亚马逊在半年内面试中考过）
- [多数元素] （亚马逊、字节跳动、Facebook 在半年内面试中考过）
- [电话号码的字母组合]（亚马逊在半年内面试常考）
- [N 皇后]（字节跳动、苹果、谷歌在半年内面试中考过）


## 作业
- [二叉树的最近公共祖先](./lowest-common-ancestor-a-binary-tree.java)（Facebook 在半年内面试常考）
- [从前序与中序遍历序列构造二叉树](./construct-binary-tree-preorder-and-inorder-traversal.java)（字节跳动、亚马逊、微软在半年内面试中考过）
- [组合]微软、亚马逊、谷歌在半年内面试中考过）
- [全排列]（字节跳动在半年内面试常考）
- [全排列II]（亚马逊、字节跳动、Facebook 在半年内面试中考过）



## 笔记
### 一、递归 Recursion
#### 解题思路
- 抛弃人肉递归
- 找最近重复子问题
    - 如何找？
- 数学归纳法

#### 递归的实现
四步法。
- terminator：递归终结条件
- process：当前层处理逻辑
- drill down：下探到下一层
- reverse/restore status：清理/存储当前层

#### 代码模版
```java
public void recur(int level, int param) {
    // terminator
    if (level > MAX_LEVEL) {
        // process: result
        return;
    }

    // process: current logic
    process(level, param);

    // drill down
    recur(level + 1, newParam);

    // restore/reverse current status

}
```

### 二、分治和回溯
分治和回溯，本质上也是递归。

#### 分治 Divide & Conquer
分治，将一个问题分解成多个相同逻辑的子问题，最后对子问题的返回结果聚合处理。

**相关问题**
- 求n的k次幂


##### 解题思路


##### 代码模版
```java
private void devide_conquer(problem, param1, param2, ...) {
    // terminator
    if (problem == null) {
        print_result
        return 
    }
    
    // process: prepare data
    data = prepare_data(problem);
    subproblems = split_problem(problem, data);
    
    // drill down: conquer subproblems
    subresult1 = devide_conquer(subproblems[0], p1, ...);
    subresult2 = devide_conquer(subproblems[1], p1, ...);
    
    // process: generate the final result
    result = process_result(subresult1, subresult2, ...);
    
    // reverse status
}
```

#### 回溯 Backtracking
回溯，采用试错的思想，尝试分布的去解决问题。即一步步地去尝试判断，当发现当前步骤不能解答，将取消上一步或上几步的计算，再尝试其他可能的分治。
- 最坏情况，可能会导致一次时间复杂度为指数的计算。

**注意事项**
- 清除当前层记录的状态
- 适当剪枝

**相关问题**
- 求组合的所有可能性，如一个数组中所有数组合的子集

