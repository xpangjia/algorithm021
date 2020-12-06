// 记录：均是从题解里看的。

// 方法一：暴力法
// 方法二：暴力法 + hash
// 方法三：夹逼法
// 方法四：夹逼加速法


// 有问题，没法去重
// class Solution {
//     public List<List<Integer>> threeSum(int[] nums) {
//         if(nums == null || nums.length < 3) {
//             return Collections.emptyList();
//         }

//         Arrays.sort(nums);

//         Set<List<Integer>> result = new LinkedHashSet<>();
//         int l = nums.length;
//         for (int i = 0; i < l - 2; i++ ) {
//             for (int j = i + 1; j < l - 1; j++) {
//                 for (int k = j + 1; k < l; k++) {
//                     if (nums[i] + nums[j] + nums[k] == 0) {
//                         result.add(Arrays.asList(nums[i], nums[j], nums[k]));
//                     }
//                 } 
//             }
//         }

//         return new ArrayList<>(result);
//     }
// }


// 方法二：
// 待修改
// class Solution {
//     public List<List<Integer>> threeSum(int[] nums) {
//         if(nums == null || nums.length < 3) {
//             return Collections.emptyList();
//         }

//         Arrays.sort(nums);

//         Set<List<Integer>> result = new LinkedHashSet<>();
//         int l = nums.length;
//         for (int i = 0; i < l - 2; i++ ) {
//             int target = -nums[i];
//             Map<Integer, Integer> hashMap = new HashMap<>(l-1);
//             int k=1;
//             for (int j = i + 1; j < l; j = j+ k) {
//                 k = 1;
//                 int v = target - nums[j];
//                 Integer exist = hashMap.get(v);
//                 if(exist != null) {
//                     List<Integer> list = Arrays.asList(nums[i], exist, nums[j]);
//                     list.sort(Comparator.naturalOrder());
//                     result.add(list);
//                 } else {
//                     hashMap.put(nums[j], nums[j]);
//                 }

//                 while(j+1 < l-1 && nums[j] == nums[j+1]) {
//                     j++;
//                 }
//             }
//         }

//         return new ArrayList<>(result);
//     }
// }



// 方法三：夹逼法
// 思路：
//      1、设置两个指针，头指针和尾指针；对数组排序
//      2、第一层为for循环，从i=0开始，设置head指针为i的下一位， tail为最后一位
//      3、第二层为while循环，当head与tail不重合时，进入判断逻辑
//          判断nums[i]是否等于头尾指针的数值和sum的负数，
//          若是，则nums[i],nums[head],nums[tail]符合要求
//          若否，则继续nums[i]与sum的大小
//      3、若nums[i]大于sum，则head指针得右移，这样sum值才可能变大
//          若小于sum，则tail指针得右移，这样sum值才可能变小
//      4、循环结束，输出结果
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        if(nums == null || nums.length < 3) {
            return Collections.emptyList();
        }

        Arrays.sort(nums);

        List<List<Integer>> result = new LinkedList<>();
        int l = nums.length;
        for (int i = 0; i < l - 2; i++) {
            // 加速1：若第一个数都大于0，后面数与其相加不可能等于0
            if(nums[i] > 0){
                return result;
            }

            // 加速2：跳过计算过的数据，防止结果重复
            if(i!=0 && nums[i] == nums[i-1]){
                continue;
            }

            int head = i+1;
            int tail = l-1;

            while(head < tail){
                int sum = -(nums[head] + nums[tail]);
                if(nums[i] == sum){
                    result.add(Arrays.asList(nums[i],nums[head],nums[tail]));

                    while(head < tail && nums[head] == nums[head +1]){
                        head++;
                    }

                    while(head < tail && nums[tail] == nums[tail-1]){
                        tail--;
                    }
                } 
                // 这里等号必须加
                if(nums[i] >= sum ){
                    tail--;
                } else {
                    head++;
                }
            }
            
        }

        return result;
    }
}