// 记录：毫无头绪，看着题解才明白。

class Solution {
    public int maxArea(int[] height) {
        int left=0, right= height.length -1;
        int area = 0, tmp = 0;
        while(left < right){
            if(height[left] < height[right]){
                tmp = height[left]*(right - left);
                left++;
            } else {
                tmp = height[right]*(right - left);
                right--;
            }
            area = area > tmp ? area:tmp;
        }
        return area;
    }
}