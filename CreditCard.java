import java.util.Scanner;

public final class CreditCard {
    int ccNumber;

    public CreditCard(int ccNumber) {
        this.ccNumber = ccNumber;
        verifyCard();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the credit card number: ");
        int ccNumber = scanner.nextInt();
        CreditCard C1 = new CreditCard(ccNumber);
        C1.display();
    }
    
    public void verifyCard() {
        String ccString = String.valueOf(ccNumber);
        if (ccString.length() < 8 || ccString.length() > 9) {
            System.out.println("Invalid credit card number");
            return;
        }

        int lastDigit = (int) (ccNumber % 10);
        int remainingNumber = ccNumber / 10;
        int reversedNumber = reverseDigits(remainingNumber);
        String reversedStr = String.valueOf(reversedNumber);

        int sum = 0;
        for (int i = 0; i < reversedStr.length(); i++) {
            int digit = Character.getNumericValue(reversedStr.charAt(i));
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit = digit / 10 + digit % 10; 
                }
            }
            sum += digit;
        }

        int result = 10 - (sum % 10);

        if (result == lastDigit) {
            System.out.println("The credit card number is valid.");
        } else {
            System.out.println("The credit card number is invalid.");
        }
    }

    public int reverseDigits(int number) {
        int reversed = 0;
        while (number != 0) {
            reversed = reversed * 10 + number % 10;
            number /= 10;
        }
        return reversed;
    }

    public void display() {}
}
