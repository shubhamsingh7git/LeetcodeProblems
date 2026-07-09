class Solution {
    public boolean isAdditiveNumber(String num) {
        int n=num.length();
        for(int i=1;i<=n/2;i++){
            if(num.charAt(0)=='0'&&i>1)
                break;
            for(int j=1;Math.max(i,j)<=n-i-j;j++){
                if(num.charAt(i)=='0'&&j>1)
                    break;
                if(check(num,0,i,i,j))
                    return true;
            }
        }
        return false;
    }
    private boolean check(String s,int start1,int len1,int start2,int len2){
        while(true){
            long a=Long.parseLong(s.substring(start1,start1+len1));
            long b=Long.parseLong(s.substring(start2,start2+len2));
            long sum=a+b;
            String next=String.valueOf(sum);
            int start3=start2+len2;
            if(!s.startsWith(next,start3)) return false;
            if(start3+next.length()==s.length()) return true;
            start1=start2;
            len1=len2;
            start2=start3;
            len2=next.length();
        }
    }
}