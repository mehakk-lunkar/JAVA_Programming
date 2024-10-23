import java.util.Scanner;

public class AlphabetWar {
    public int[] leftSideStrengths;
    public int[] rightSideStrengths;
    public char[] leftSideLetters = {'w', 'p', 'b', 's'};
    public char[] rightSideLetters = {'m', 'q', 'd', 'z'};

    public AlphabetWar() {
        leftSideStrengths = new int[]{4, 3, 2, 1};  
        rightSideStrengths = new int[]{4, 3, 2, 1}; 
    }

    public AlphabetWar(char[] leftLetters, int[] leftStrengths, char[] rightLetters, int[] rightStrengths) {
        this.leftSideLetters = leftLetters;
        this.leftSideStrengths = leftStrengths;
        this.rightSideLetters = rightLetters;
        this.rightSideStrengths = rightStrengths;
    }

    public String alphabetWar(String word) {
        return alphabetWar(word, word);
    }

    public String alphabetWar(String leftSideWord, String rightSideWord) {
        int leftScore = calculateLeftScore(leftSideWord);
        int rightScore = calculateRightScore(rightSideWord);

        if (leftScore > rightScore) {
            return "Left side wins!";
        } else if (rightScore > leftScore) {
            return "Right side wins!";
        } else {
            return "Let's fight again!";
        }
    }

    public int calculateLeftScore(String word) {
        int score = 0;
        for (char letter : word.toCharArray()) {
            int index = indexOf(leftSideLetters, letter);
            if (index != -1) {
                score += leftSideStrengths[index];
            }
        }
        return score;
    }

    public int calculateRightScore(String word) {
        int score = 0;
        for (char letter : word.toCharArray()) {
            int index = indexOf(rightSideLetters, letter);
            if (index != -1) {
                score += rightSideStrengths[index];
            }
        }
        return score;
    }

    public int indexOf(char[] array, char letter) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == letter) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AlphabetWar defaultGame = new AlphabetWar();
        System.out.println("Test Cases with Default Strengths:");
        System.out.println("Test Case 1: " + defaultGame.alphabetWar("z"));              
        System.out.println("Test Case 2: " + defaultGame.alphabetWar("zdqmwpbs"));      
        System.out.println("Test Case 3: " + defaultGame.alphabetWar("wwwwwwz"));        

        System.out.println("\nEnter a word for the alphabet war (with default strengths): ");
        String defaultWord = scanner.nextLine();
        System.out.println("Result: " + defaultGame.alphabetWar(defaultWord));

        System.out.println("\nWant to try with separate left and right words? (yes/no)");
        String trySeparateDefault = scanner.nextLine();

        if (trySeparateDefault.equalsIgnoreCase("yes")) {
            System.out.print("Enter the left side word: ");
            String leftWord = scanner.nextLine();

            System.out.print("Enter the right side word: ");
            String rightWord = scanner.nextLine();

            System.out.println("Result: " + defaultGame.alphabetWar(leftWord, rightWord));
        }

        System.out.println("\nWould you like to customize the letters and their strengths? (yes/no)");
        String customizeLettersAndStrengths = scanner.nextLine();

        if (customizeLettersAndStrengths.equalsIgnoreCase("yes")) {
            System.out.print("Enter left-side letters (e.g., abc): ");
            String leftSideInput = scanner.nextLine();
            char[] userLeftLetters = leftSideInput.toCharArray();
            int[] userLeftStrengths = new int[userLeftLetters.length];
            for (int i = 0; i < userLeftLetters.length; i++) {
                System.out.print("Enter strength for '" + userLeftLetters[i] + "': ");
                userLeftStrengths[i] = scanner.nextInt();
            }
            scanner.nextLine();  

            System.out.print("Enter right-side letters (e.g., xyz): ");
            String rightSideInput = scanner.nextLine();
            char[] userRightLetters = rightSideInput.toCharArray();
            int[] userRightStrengths = new int[userRightLetters.length];
            for (int i = 0; i < userRightLetters.length; i++) {
                System.out.print("Enter strength for '" + userRightLetters[i] + "': ");
                userRightStrengths[i] = scanner.nextInt();
            }
            scanner.nextLine();  

            AlphabetWar userCustomGame = new AlphabetWar(userLeftLetters, userLeftStrengths, userRightLetters, userRightStrengths);

            System.out.print("\nEnter a word for the alphabet war (with your custom letters and strengths): ");
            String customUserInput = scanner.nextLine();
            System.out.println("Result: " + userCustomGame.alphabetWar(customUserInput));

            System.out.println("\nWant to try with separate left and right words (with custom letters and strengths)? (yes/no)");
            String trySeparateCustom = scanner.nextLine();

            if (trySeparateCustom.equalsIgnoreCase("yes")) {
                System.out.print("Enter the left side word: ");
                String customLeftWord = scanner.nextLine();

                System.out.print("Enter the right side word: ");
                String customRightWord = scanner.nextLine();

                System.out.println("Result: " + userCustomGame.alphabetWar(customLeftWord, customRightWord));
            }
        }
    }
}
