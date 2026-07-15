class Solution {
    public String removeDuplicates(String s) {
        Stack <Character> st= new Stack<>();
        for(int i=0;i<s.length();i++){
            if (st.isEmpty()){
                st.push(s.charAt(i));
            }
            else {
                if(s.charAt(i)!=st.peek()){
                    st.push(s.charAt(i));
                }
                else{
                    st.pop();
                }
            }
        }
       StringBuilder ans = new StringBuilder();

        for(char ch : st) {
            ans.append(ch);
        }

        return ans.toString();
    }
}