import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {
    private final static Scanner scanner = new Scanner(System.in);

    private static int m;
    private static int n;
    private static int r;

    private static int realRotationsAmount;

    public static void main(String[] args) throws IOException {
        m = scanner.nextInt(); // rows amount
        n = scanner.nextInt(); // columns amount
        r = scanner.nextInt(); // rotations amount

        int[][] resultMatrix = new int[m][n];

        int elementsAmount = m * r;

        for (int row = 0; row < m; row++) {
            for (int column = 0; column < n; column++) {
                int rowDelta = Math.min(m - row - 1, row);
                int columnDelta = Math.min(n - column - 1, column);
                int squareDelta = Math.min(rowDelta, columnDelta);

                int squareRowsAmount = m - 2 * squareDelta;
                int squareColumnsAmount = n - 2 * squareDelta;

                int uniquePlacesAmount = 2 * (m - squareDelta * 2 - 1) + 2 * (n - squareDelta * 2 - 1); //(m - squareDelta*2) * (n - squareDelta*2) - 1;

                //System.out.println("DEBUG: rc {" + row + "," + column + "}; delta {" + squareDelta + "}; places " + uniquePlacesAmount);

/*                if(row == 3 && column == 2) {
                    int a = 0;
                }*/

                int uniqueRotationsAmount = uniquePlacesAmount - 1;
                realRotationsAmount = r % uniqueRotationsAmount;

                // todo: or if last row/column check
                // if row = squareDelta: minus column
                // else if column = squareDelta: plus row
                // else if row = m-squareDelta: plus column
                // else minus row

                int newRow = row;
                int newColumn = column;

                if(row == squareDelta){
                    newColumn = rotateLeft(newColumn, squareDelta);
                    newRow = rotateDown(newRow, squareDelta);
                    newColumn = rotateRight(newColumn, squareDelta);
                    newRow = rotateUp(newRow, squareDelta);
                }else if(column == squareDelta){
                    newRow = rotateDown(newRow, squareDelta);
                    newColumn = rotateRight(newColumn, squareDelta);
                    newRow = rotateUp(newRow, squareDelta);
                    newColumn = rotateLeft(newColumn, squareDelta);
                }else if(row == m-squareDelta-1){
                    newColumn = rotateRight(newColumn, squareDelta);
                    newRow = rotateUp(newRow, squareDelta);
                    newColumn = rotateLeft(newColumn, squareDelta);
                    newRow = rotateDown(newRow, squareDelta);
                }else{
                    newRow = rotateUp(newRow, squareDelta);
                    newColumn = rotateLeft(newColumn, squareDelta);
                    newRow = rotateDown(newRow, squareDelta);
                    newColumn = rotateRight(newColumn, squareDelta);
                }

                //System.out.println("DEBUG: rc: {" + newRow + "," + newColumn + "}");

                resultMatrix[newRow][newColumn] = scanner.nextInt();
            }
        }

        for (int row = 0; row < m; row++) {
            for (int column = 0; column < n; column++) {
                System.out.print(resultMatrix[row][column] + " ");
            }
            System.out.println();
        }
    }

    private static int rotateDown(int newRow, int squareDelta){
        if(realRotationsAmount > 0) {
            int oldRow = newRow;
            newRow += realRotationsAmount;

            if (newRow >= m - squareDelta) {
                newRow = m - squareDelta - 1;
            }

            realRotationsAmount -= newRow - oldRow;
        }

        return newRow;
    }

    private static int rotateRight(int newColumn, int squareDelta){
        if(realRotationsAmount > 0) {
            int oldColumn = newColumn;
            newColumn += realRotationsAmount;

            if (newColumn >= n - squareDelta) {
                newColumn = n - squareDelta - 1;
            }

            realRotationsAmount -= newColumn - oldColumn;
        }

        return newColumn;
    }

    private static int rotateUp(int newRow, int squareDelta){
        if(realRotationsAmount > 0) {
            int oldRow = newRow;
            newRow -= realRotationsAmount;

            if (newRow < squareDelta) {
                newRow = squareDelta;
            }

            realRotationsAmount -= oldRow-newRow;
        }

        return newRow;
    }

    private static int rotateLeft(int newColumn, int squareDelta){
        if(realRotationsAmount > 0) {
            int oldColumn = newColumn;
            newColumn -= realRotationsAmount;

            if (newColumn < squareDelta) {
                newColumn = squareDelta;
            }

            realRotationsAmount -= oldColumn-newColumn;
        }

        return newColumn;
    }

    /*
        1 8 7
        2   6
        3 4 5
        3x3 matrix -> 8 unique places -> 7 unique rotations
         */
    /*
        00 01 02 03 04 05
        10 11 12 13 14 15
        20 21 22 23 24 25
        30 31 32 33 34 35
        40 41 42 43 44 45
         */
}
