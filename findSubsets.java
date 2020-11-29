import java.util.ArrayList;

public class findSubsets {
    // To store the states of DP
    static boolean[][] dp; 
    // Collection of all possible subsets
    static ArrayList<ArrayList<Integer>> allSubsets = new ArrayList<ArrayList<Integer>>();

    static void printSubsets(int arr[], int i, int sum, ArrayList<Integer> curr) { 
        // If sum becomes 0, we find the subset 
        if (i == 0 && sum == 0) { 
            ArrayList<Integer> newSet = new ArrayList<>(curr);
            allSubsets.add(newSet);
            curr.clear(); 
            return; 
        }
        // If given sum can be achieved after ignoring 
        // current element. 
        if (dp[i-1][sum]) { 
            // Create a new list to store path 
            ArrayList<Integer> b = new ArrayList<>(); 
            b.addAll(curr); 
            printSubsets(arr, i-1, sum, b); 
        } 
        // If given sum can be achieved after considering 
        // current element. 
        if (sum >= arr[i-1] && dp[i-1][sum-arr[i-1]]) { 
            curr.add(i); 
            printSubsets(arr, i-1, sum-arr[i-1], curr); 
        } 
    } 
    
    // Builds dp and collects all subsets
    // Prints the number of subsets and the first subset
    static void compute(int arr[], int n, int sum) { 
        if (n == 0 || sum < 0){
           return;
        }
        dp = new boolean[n+1][sum + 1];
        for (int i = 0; i <= n; i++) { 
            dp[i][0] = true;   
        }
        for (int j = 1; j <= sum; j++){
            dp[0][j] = false;   
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j >= arr[i-1]){
                    dp[i][j] = (dp[i-1][j] || dp[i-1][j-arr[i-1]]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }                               
        if (dp[n][sum] == false) { 
            System.out.println("No subsets sum to" + sum); 
            return; 
        } 
       
        // Traverse from dp[n][sum] to find all possible subsets
        ArrayList<Integer> curr= new ArrayList<>(); 
        printSubsets(arr, n, sum, curr); 
        System.out.println(allSubsets.size());
        System.out.println(allSubsets.get(0));
    } 

    public static void main(String []args){
        int arr[] = {1,1,1,1,1,1,1,1,1,1};
        int n = arr.length; 
        int sum = 2; 
        compute(arr, n, sum); 
     }
}

