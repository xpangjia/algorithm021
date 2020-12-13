// 记录：
// 字母异味词：字母的组成字符串完全相同，只是字母的顺序不同的两个字符串
// 方法一：暴力法
// 方法二：哈希表


// 方法一：暴力法
// 思路：先sort，然后比较sort后的两个字符串是否相等
// 时间复杂度：O(n*logn)
//           =  排序 O(n*logn) + 比较O(n)
// class Solution {
//     public boolean isAnagram(String s, String t) {
//         if (s.length() != t.length()) {
//             return false;
//         }

//         // 转成数组后，进行排序
//         char[] sArr = s.toCharArray();
//         char[] tArr = t.toCharArray();

//         Arrays.sort(sArr);
//         Arrays.sort(tArr);

//         return Arrays.equals(sArr, tArr);
//     }
// }



// 方法二：哈希表，统计字母的频次
// 思路：维护一个哈希表，遍历字符串s，统计每个字符出现的次数，然后遍历字符串t，依次减去每个字符串
// 时间复杂度：O(n)
//          = 2 * O(n), n为s的长度
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<Character, Integer>();

        // 遍历字符串s，统计字母的频次
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // 遍历字符串t，减去对应字母词频，如果出现词频为负，则说明t多出来s没有的字母
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            map.put(c, map.getOrDefault(c, 0) - 1);
            
            if (map.get(c) < 0) {
                return false;
            }
        }

        return true;
    }
}

