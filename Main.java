public class Main{
    public static void main(String[] args) {   
        
        // Fibo-Rec
        System.out.println("Fibo-Rec (4): " + fiboRec(4));
        System.out.println("Fibo-Rec (8): " + fiboRec(8));
        System.out.println("Fibo-Rec (16): " + fiboRec(16));
        System.out.println("Fibo-Rec (32): " + fiboRec(32));

        // Knapsack
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int maxWeight = 50;
        int maxVal = knapsack(values, weights, maxWeight);
        System.out.println("Valor máximo obtido: " + maxVal);

        // Strings de teste
        String s1 = "Casablanca";
        String s2 = "Portentoso";
        String s3 = "Maven, a Yiddish word meaning accumulator of knowledge, began as an attempt to " +
   			"simplify the build processes in the Jakarta Turbine project. There were several" + 
   			" projects, each with their own Ant build files, that were all slightly different." +
   			"JARs were checked into CVS. We wanted a standard way to build the projects, a clear "+ 
   			"definition of what the project consisted of, an easy way to publish project information" +
   			"and a way to share JARs across several projects. The result is a tool that can now be" +
   			"used for building and managing any Java-based project. We hope that we have created " +
   			"something that will make the day-to-day work of Java developers easier and generally help " +
   			"with the comprehension of any Java-based project.";
        String s4 = "This post is not about deep learning. But it could be might as well. This is the power of " +
   			"kernels. They are universally applicable in any machine learning algorithm. Why you might" +
   			"ask? I am going to try to answer this question in this article." + 
   		    "Go to the profile of Marin Vlastelica Pogančić" + 
   		    "Marin Vlastelica Pogančić Jun";

        // Distância de Edição - Solução Iterativa
        System.out.println("Iterativa - Distância para " + s1 + ", " + s2 + ": " + distanciaEdicao(s1, s2));

        // Distância de Edição - Solução Iterativa
        System.out.println("Recursiva - Distância para " + s1 + ", " + s2 + ": " + distanciaEdicaoRec(s1, s2));

        // Distância de Edição - Programação Dinâmica
        System.out.println("Prog Din. - Distância para " + s1 + ", " + s2 + ": " + distanciaEdicaoProgDin(s1, s2));

        // Distância de Edição - Solução Iterativa
        System.out.println("Iterativa - Distância para " + s3.substring(0, 7) + ", " + s4.substring(0, 7) + ": " + distanciaEdicao(s3, s4));

        // Distância de Edição - Solução Iterativa
        System.out.println("Recursiva - Distância para " + s3.substring(0, 7) + ", " + s4.substring(0, 7) + ": " + distanciaEdicaoRec(s3, s4));

        // Distância de Edição - Programação Dinâmica
        System.out.println("Prog Din. - Distância para " + s3.substring(0, 7) + ", " + s4.substring(0, 7) + ": " + distanciaEdicaoProgDin(s3, s4));

    }

    public static int fiboRec(int n){
        if(n <= 1) return n;
        int a = fiboRec(n - 1);
        int b = fiboRec(n - 2);
        return a+b;
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

    // Distância de Edição - Solução Iterativa
    public static int distanciaEdicao(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
    
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
                }
            }
        }
    
        return dp[s1.length()][s2.length()];
    }

    public static int distanciaEdicaoRec(String s1, String s2) {
        int[][] memo = new int[s1.length()+1][s2.length()+1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                memo[i][j] = -1;
            }
        }
        return distanciaEdicaoAux(s1, s2, s1.length(), s2.length(), memo);
    }

    private static int distanciaEdicaoAux(String s1, String s2, int i, int j, int[][] memo) {
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (i == 0) {
            memo[i][j] = j;
            return j;
        }
        if (j == 0) {
            memo[i][j] = i;
            return i;
        }
        int custo = (s1.charAt(i-1) == s2.charAt(j-1)) ? 0 : 1;
        int inserir = distanciaEdicaoAux(s1, s2, i, j-1, memo) + 1;
        int remover = distanciaEdicaoAux(s1, s2, i-1, j, memo) + 1;
        int substituir = distanciaEdicaoAux(s1, s2, i-1, j-1, memo) + custo;
        int min = Math.min(Math.min(inserir, remover), substituir);
        memo[i][j] = min;
        return min;
    }

    // Distância de Edição - Programação Dinamica
    public static int distanciaEdicaoProgDin(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
    
        int[][] dist = new int[m + 1][n + 1];
    
        for (int i = 0; i <= m; i++) {
            dist[i][0] = i;
        }
    
        for (int j = 0; j <= n; j++) {
            dist[0][j] = j;
        }
    
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dist[i][j] = dist[i - 1][j - 1];
                } else {
                    dist[i][j] = 1 + Math.min(dist[i][j - 1], Math.min(dist[i - 1][j], dist[i - 1][j - 1]));
                }
            }
        }
        return dist[m][n];
    }
}
