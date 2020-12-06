// 记录：方法一和方法二均是通过题解学习的思路。


// 方法一：双指针法遍历两次
// 思路：
//      1、先遍历第一次，把所有非0数移到最左边；剩下的就是0与重复的非0数；
//      2、遍历第二次，把剩下所有数赋值为0

// class Solution {
//     public void moveZeroes(int[] nums) {
//         int l;
//         if(nums == null || (l=nums.length) == 0) {
//             return;
//         }

//         int j=0;
//         for(int i=0; i<l; i++) {
//             // 遇到0跳过，指针i继续往前走；
//             // 需要非0，把指针i指向的非0，赋值给前面的指针j，赋值完指针j进1
//             // 循环完毕，指针j左边，就都是非0数，右边则是0以及重复的非0
//             if(nums[i] != 0) {
//                 nums[j++] = nums[i];
//             }
//         }
//         // 把指针j右边的数都赋值0
//         for(int i=j; i<l; i++) {
//             nums[i] = 0;
//         }
//     }
// }


// 方法一优化：
// 思路：

class Solution {
    public void moveZeroes(int[] nums) {
        int l;
        if(nums == null || (l=nums.length) == 0) {
            return;
        }

        int count = 0;
        for(int i=0; i<l; i++) {
            if(nums[i] == 0) {
                count++;
            } else {
                nums[i - count] = nums[i];
            }
        }
        // 把指针j右边的数都赋值0
        for(int i=0; i<count; i++) {
            nums[l - count + i] = 0;
        }
    }
}


// 方法二：双指针遍历一次
// 思路：两个指针，指针i用来不停移动，找寻非0数，直到遍历结束；指针j用来定位0，定位到0后，指针i就一直对应0，不停与非0数交换；

// class Solution {
//     public void moveZeroes(int[] nums) {
//         int l;
//         if(nums == null || (l=nums.length) == 0) {
//             return;
//         }

//         int j=0;
//         int tmp = 0;
//         for(int i=0; i<l; i++) {
//             // 如果遇到0，则i继续前行，j不动
//             if(nums[i] != 0) {
//                 // 如果i == j,说明还未找到第一个非0数，无需交换，两个指针同时前进即可
//                 if(i > j){
//                     nums[j] = nums[i];
//                     nums[i] = 0;
//                 }
//                 // j++，则指针j移动到了0的新位置，一直跟随着0
//                 j++;
//             }
//         }
//     }
// }