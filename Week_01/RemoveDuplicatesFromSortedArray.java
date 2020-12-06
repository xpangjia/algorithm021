// 记录：自己的思路，尚未看题解，还不清楚有什么解法。

// 自己的思路：
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        // 设置两个指针，一个single指针，指向第一次出现的数字，一个i指针，用于循环移动；
        // 因为数组排序过了，所以当一个数出现一次之后，后面的数字，一旦与他不相同，则剩下的数字也肯定不相同
        // 初始化，single指针，指向第一个数字
        int single = 0;
        for(var i=1; i< nums.length; i++) {
            // 若i指向的数字不重复，则single下标右移，用于保存不重复的数字
            if(nums[i] != nums[single]) {
                single++;
                nums[single] = nums[i];
            }
            // 若重复，则i右移，继续进入循环判断是否与当前数字重复
        }
        return single+1;
    }
}