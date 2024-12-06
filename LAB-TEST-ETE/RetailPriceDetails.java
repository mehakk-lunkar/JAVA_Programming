import java.util.Scanner;

interface PriceDetails { //Create an interface
    int countPricePairs(int[] arr, int target);
}

class Price implements PriceDetails { //Create a class

    @Override
    public int countPricePairs(int[] arr, int target) {
        int pairCount = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    pairCount++;
                }
            }
        }
        return pairCount;
    }
}

public class RetailPriceDetails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User input for array size
        System.out.print("Enter the number of items in the transaction: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];

        // User input for array elements
        System.out.println("Enter the item prices:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // User input for target sum
        System.out.print("Enter the target sum: ");
        int target = scanner.nextInt();

        PriceDetails transaction = new Price();
        int result = transaction.countPricePairs(arr, target);

        // Output
        System.out.println("Number of pairs: " + result);
    }
}
