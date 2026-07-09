class Solution {
    public String removeDuplicateLetters(String s) {
        int[]freq=new int[26];
        for(char c:s.toCharArray()){
            freq[c-'a']++;
        }
        boolean[]vis=new boolean[26];
        Stack<Character>st=new Stack<>();
        for(char c:s.toCharArray()){
            freq[c-'a']--;
            if(vis[c-'a']){
                continue;
            }
            while(!st.isEmpty() && st.peek()>c && freq[st.peek()-'a']>0){
                vis[st.pop()-'a']=false;
            }
            st.push(c);
            vis[c-'a']=true;
        }
        StringBuilder sb=new StringBuilder();
        while(!st.isEmpty()){
            sb.append(st.pop());
        }
        return sb.reverse().toString();
    }
}