class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer>st=new Stack<>();
        for(int a:asteroids){
            boolean alive=true;
            while(alive && !st.isEmpty() && st.peek()>0 && a<0){
                if(st.peek()<-a){
                    st.pop();
                }
                else if(st.peek()==-a){
                    st.pop();
                    alive=false;
                }
                else{
                    alive=false;
                }
            }
            if(alive){
                st.push(a);
            }
        }
        int[] ans=new int[st.size()];
        for(int i=ans.length-1;i>=0;i--){
            ans[i]=st.pop();
        }
        return ans;
    }
}