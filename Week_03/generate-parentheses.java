// 记录：
// 方法一：暴力法

class Solution {
    ArrayList<String> result = new ArrayList<String>();

    public List<String> generateParenthesis(int n) {
        _generate(0, 0, n, "");
        return result;
    }

    private void _generate (int left, int right, int n, String s) {
        // terminator
        if (left == n && right == n) {
            result.add(s);
            return;
        }

        // process 
        if (left < n) {
            _generate(left+1, right, n, s + "(");
        }

        // right < left < n
        if(left > right) {
            _generate(left, right+1, n, s + ")");
        }

        // reverse states
    }
}