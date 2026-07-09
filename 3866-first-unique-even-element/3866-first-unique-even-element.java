class Solution {
    public int firstUniqueEven(int[] nums) {
        int count[]=new int[101];
        for(int x:nums){
            count[x]++;
        }
        for(int x:nums){
            if(x%2==0 && count[x]==1){
                return x;
            }
        }
        return -1;
    }
}