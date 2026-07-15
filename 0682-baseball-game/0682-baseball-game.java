class Solution {
    public int calPoints(String[] operations) {

        Stack<Integer> st = new Stack<>();

        for (String op : operations) {

            if (op.equals("C")) {
                // Remove the previous valid score
                st.pop();
            }

            else if (op.equals("D")) {
                // Double the previous valid score
                st.push(2 * st.peek());
            }

            else if (op.equals("+")) {
                // Sum of the previous two valid scores
                int first = st.pop();
                int second = st.peek();

                st.push(first);              // Restore the top element
                st.push(first + second);     // Push the new score
            }

            else {
                // It is a number (can be positive or negative)
                st.push(Integer.parseInt(op));
            }
        }

        int sum = 0;

        while (!st.isEmpty()) {
            sum += st.pop();
        }

        return sum;
    }
}