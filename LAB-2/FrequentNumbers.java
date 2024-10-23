import java.util.Arrays;
import java.util.Scanner;

public class FrequentNumbers {
    static int[] numbers;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();
        numbers = new int[n];

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        System.out.print("Enter the value of K: ");
        int K = scanner.nextInt();

        System.out.print("Output: ");
        findFrequentNumbers(K);

    }

    static void findFrequentNumbers(int K) {
        int maxNum = Arrays.stream(numbers).max().orElse(0); 
        int[] frequency = new int[maxNum + 1];

        for (int number : numbers) {
            frequency[number]++;
        }

        int[][] freqArray = new int[frequency.length][2];

        for (int i = 0; i < frequency.length; i++) {
            freqArray[i][0] = i; 
            freqArray[i][1] = frequency[i]; 
        }

        Arrays.sort(freqArray, (a, b) -> {
            if (b[1] == a[1]) {
                return Integer.compare(b[0], a[0]); 
            }
            return Integer.compare(b[1], a[1]); 
        });

        for (int i = 0; i < K; i++) {
            System.out.print(freqArray[i][0]);
            if (i < K - 1) {
                System.out.print(" "); 
            }
        }
        System.out.println();
    }
}
