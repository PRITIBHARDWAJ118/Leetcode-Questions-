class Solution {
    public int maxArea(int[] height) {
        int lp=0;
        int rp=height.length-1;
        int ht,wt,area;
        int max= Integer.MIN_VALUE;
        while(lp<rp){
            ht=Math.min(height[lp],height[rp]);
            wt=rp-lp;
            area= ht*wt;
            max= Math.max(area,max);
            if(height[lp]<height[rp]){
                lp++;
            }
            else {
                rp--;
            }
        }
        return max;
    }
}