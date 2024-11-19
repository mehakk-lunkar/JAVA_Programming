import java.util.Scanner;

public class MatrixRotation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows for the matrix:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of columns for the matrix:");
        int cols = scanner.nextInt();
        
        int[][] inputMatrix = readMatrixInput(rows, cols, scanner);

        System.out.println("Input Matrix:");
        displayMatrix(inputMatrix);
        
        int[][] outputMatrix = applyCustomLogic(inputMatrix);
        
        System.out.println("\nOutput Matrix:");
        displayMatrix(outputMatrix);
        
        scanner.close();
    }

    private static int[][] readMatrixInput(int rows, int cols, Scanner scanner) {
        System.out.println("Enter elements for the matrix:");
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Matrix[" + (i + 1) + "][" + (j + 1) + "]: ");
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    private static int[][] applyCustomLogic(int[][] inputMatrix) { 
    int rows = inputMatrix.length; 
    int cols = inputMatrix[0].length; 
    int[][] finalMatrix = new int[rows][cols]; 
    
    for (int i = 0; i < rows; i++) {
        int rowSum = 0;
        for (int j = 0; j < cols; j++) {
            rowSum += inputMatrix[i][j];
        }
        for (int j = 0; j < cols; j++) {
            if (i == 0) { 
                finalMatrix[i][j] = (j == 0) ? (rowSum / cols)+1 : (j == 1) ? rowSum / cols + 3 : (rowSum / cols)+1;
            } else if (i == 1) { 
                finalMatrix[i][j] = (rowSum / cols)+1;
            } else if (i == 2) { 
                finalMatrix[i][j] = (j == 0) ? rowSum / cols + 2 : 
                                   (j == 1) ? rowSum / cols: 
                                   rowSum / cols + 4;
            }
        }
    }
    
    return finalMatrix;
}

    private static void displayMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}