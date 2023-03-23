public class Main{
    public static void main(String[] args) {   
        
        // Knapsack
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int maxWeight = 50;
        int maxVal = knapsack(values, weights, maxWeight);
        System.out.println("Valor máximo obtido: " + maxVal);


    }

    // Knapsack em programação dinâmica
    public static int knapsack(int[] values, int[] weights, int maxWeight) {
        int n = values.length;
        int[][] dp = new int[n + 1][maxWeight + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= maxWeight; j++) {
                if (weights[i-1] <= j) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weights[i-1]] + values[i-1]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        
        return dp[n][maxWeight];
    }
}