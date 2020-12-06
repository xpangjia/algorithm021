// 利用栈
class Solution {
    public boolean isValid(String s) {
        int n = s.length();
        if(n % 2 == 1) {
            return false;
        }

        Map<Character, Character> paris = new HashMap<Character, Character>();
        paris.put(')', '(');
        paris.put(']', '[');
        paris.put('}', '{');

        Deque<Character> stack = new LinkedList<Character>();
        
        for(int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            // 判断是否属于key值，也就是是否属于左标签
            if(paris.containsKey(ch)) {
                // 若为右标签
                // 若栈为空，或者栈顶值（与其最近左相邻可配对的字符串不为其对应的右标签）
                if(stack.isEmpty() || stack.peek() != paris.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
            // 若是左标签，先入栈
                stack.push(ch);
            }
        }

        return stack.isEmpty();
    }
}