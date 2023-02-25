import java.util.Scanner;

public class Solution {
    private final static Scanner scanner = new Scanner(System.in);

    private static int m;
    private static int n;
    private static int r;

    private static int newRow;
    private static int newColumn;

    public static void main(String[] args) {
        m = scanner.nextInt(); // rows amount
        n = scanner.nextInt(); // columns amount
        r = scanner.nextInt(); // rotations amount

        int[][] matrix = new int[m][n];

        for (int row = 0; row < m; row++) {
            for (int column = 0; column < n; column++) {
                int squareDelta = calculateSquareDelta(row, column);
                int realRotationsAmount = calculateRealRotationsAmount(squareDelta);

                newRow = row;
                newColumn = column;

                changeElementPosition(squareDelta, realRotationsAmount);

                matrix[newRow][newColumn] = scanner.nextInt();
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int calculateSquareDelta(int row, int column) {
        int rowDelta = Math.min(m - row - 1, row);
        int columnDelta = Math.min(n - column - 1, column);

        return Math.min(rowDelta, columnDelta);
    }

    private static int calculateRealRotationsAmount(int squareDelta) {
        int uniqueRotationsAmount = 2 * (n + m - 4 * squareDelta);

        if (n > 2 && m > 2) {
            uniqueRotationsAmount -= 4; // remove excess corner rotations
        }

        return r % uniqueRotationsAmount;
    }

    private static void changeElementPosition(int squareDelta, int realRotationsAmount) {
        int lowestRow = m - squareDelta - 1;
        int rightColumn = n - squareDelta - 1;

        for (int i = 0; i < realRotationsAmount; i++) {
            if (newRow == squareDelta) { // If upper row
                if (newColumn > squareDelta) { // And if not left column
                    newColumn -= 1; // Then move to the left
                } else {
                    newRow += 1; // Then move down
                }
            } else if (newRow == lowestRow) {
                if (newColumn < rightColumn) { // If not right column
                    newColumn += 1; // Move to the right
                } else {
                    newRow -= 1; // Move up
                }
            } else if (newColumn == squareDelta) { // If left row
                if (newRow < lowestRow) {
                    newRow += 1; // Move down
                } else {
                    newColumn += 1; // Move to the right
                }
            } else if (newColumn == rightColumn) {
                if (newRow > squareDelta) { // If not upper row
                    newRow -= 1; // Move up
                } else {
                    newColumn -= 1; // Move to the left
                }
            }
        }
    }
}