import java.util.ArrayList;

public class countSubsets {
    // To store the states of DP
    static long[][] dp;
    
    // Function to return the total number of subsets 
    static void findCnt(long []arr, int n, long sum) { 
        dp = new long[n+1][(int)sum+1]; 

        for (int i = 0; i <= n; i++) { 
            dp[i][0] = 1;   
        }
        for (int j = 1; j <= sum; j++){
            dp[0][j] = 0;   
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j >= arr[i-1]){
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-(int) arr[i-1]];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        } 
        System.out.println("The total number of subests is: " + dp[n][(int)sum]); 
    } 

    // Find and print indexes and corresponding values of one of the subsets
    static void printSubset(long []arr, int n, long sum) {
        ArrayList<Integer> subset = new ArrayList<>();
        int i = n; int j = (int)sum;
        while(i > 0 && j > 0){
            if (dp[i-1][j] != dp[i][j]) {
                subset.add(i);
                j-=(int)arr[i-1];
            }
            i--;
        }
        System.out.println("Indexes: " + subset);
        System.out.print("Values: ");
        for (int index: subset){
            System.out.print(arr[index-1]+", ");
        }
    }

    public static void main(String []args)  { 
        long arr[] = {9,3,11,6,55,9,7,3,3,29,16,4,4,20,11,6,6,8,8,4,10,11,16,10,
            6,10,3,5,6,4,14,5,29,15,3,18,7,7,20,4,9,3,11,38,6,3,13,12,5,10,3}; 
        //long arr[] = {1,2,2,4,2};
        int n = arr.length; 
        long sum = 269;
        findCnt(arr, n, sum); 
        printSubset(arr, n, sum);
    }
}
