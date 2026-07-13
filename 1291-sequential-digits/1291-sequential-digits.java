class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
      List<Integer> result = new ArrayList<>();
        String sample = "123456789";
        
        // Find the length of the bounds to avoid generating unnecessary numbers
        int lowLen = String.valueOf(low).length();
        int highLen = String.valueOf(high).length();
        
        // Iterate through all possible lengths
        for (int length = lowLen; length <= highLen; length++) {
            // Iterate through all possible starting positions for that length
            for (int start = 0; start <= 9 - length; start++) {
                
                String sub = sample.substring(start, start + length);
                int num = Integer.parseInt(sub);
                
                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }
        
        return result;
    }  
    }
