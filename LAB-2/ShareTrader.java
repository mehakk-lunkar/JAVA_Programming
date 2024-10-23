import java.util.Scanner;

public class ShareTrader {
    static int maxProfit = 0;

    public static int findMaxProfit(int[] prices) {
        int n = prices.length;
        if (n < 2) return 0;

        int[] firstTransaction = new int[n];
        int[] secondTransaction = new int[n];

        int minPrice = prices[0];
        firstTransaction[0] = 0;
        for (int i = 1; i < n; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            firstTransaction[i] = Math.max(firstTransaction[i - 1], prices[i] - minPrice);
        }

        int maxPrice = prices[n - 1];
        secondTransaction[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            maxPrice = Math.max(maxPrice, prices[i]);
            secondTransaction[i] = Math.max(secondTransaction[i + 1], maxPrice - prices[i]);
        }

        for (int i = 0; i < n; i++) {
            maxProfit = Math.max(maxProfit, firstTransaction[i] + secondTransaction[i]);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of stock prices: ");
        int n = scanner.nextInt();

        int[] prices = new int[n];
        System.out.println("Enter the stock prices:");
        for (int i = 0; i < n; i++) {
            prices[i] = scanner.nextInt();
        }
        int result = findMaxProfit(prices);
        System.out.println("Maximum Profit: " + result);
    }
}
