class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int n=cardPoints.length;
        if(k==n){
            int sum=0;
            for(int x:cardPoints){
                sum+=x;
            }
            return sum;
        }
        int total=0;
        for(int x:cardPoints){
            total+=x;
        }
        int window=n-k;
        int curr=0;
        for(int i=0;i<window;i++){
            curr+=cardPoints[i];
        }
        int minWindow=curr;
        for(int i=window;i<n;i++){
            curr+=cardPoints[i];
            curr-=cardPoints[i-window];
            minWindow=Math.min(minWindow,curr);
        }
        return total-minWindow;

    }
}