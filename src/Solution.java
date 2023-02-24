import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {
    private final static Scanner scanner = new Scanner(System.in);

    private static int m;
    private static int n;
    private static int r;

    public static void main(String[] args) throws IOException {
        m = scanner.nextInt(); // rows amount
        n = scanner.nextInt(); // columns amount
        r = scanner.nextInt(); // rotations amount

        int[][] resultMatrix = new int[m][n];

        int elementsAmount = m * r;

        for(int row = 0; row < m; row++){
            for(int column = 0; column < n; column++){
                int rowDelta = Math.min(m - row - 1, row);
                int columnDelta = Math.min(n - column - 1, column);
                int squareDelta = Math.min(rowDelta, columnDelta);

                int uniquePlacesAmount = 2* (m - squareDelta*2 -1) + 2*(n - squareDelta*2-1); //(m - squareDelta*2) * (n - squareDelta*2) - 1;

                System.out.println("DEBUG: rc {"+ row + "," + column + "}; delta {" + squareDelta + "}; places " + uniquePlacesAmount);

                if(row == 3 && column == 0) {
                    int a = 0;
                }

                int uniqueRotationsAmount = uniquePlacesAmount - 1;
                int realRotationsAmount = r % uniqueRotationsAmount;

                int newRow = row;
                int newColumn = column;

                // todo: or if last row/column check
                if(realRotationsAmount > m - rowDelta*2 ){
                    newRow = m - rowDelta -1;
                    realRotationsAmount -= m - rowDelta*2;

                    if(realRotationsAmount > n - columnDelta*2){
                        newColumn = n - columnDelta-1;
                        realRotationsAmount -= n - columnDelta*2;

                        if(realRotationsAmount > m - rowDelta*2){
                            newRow = rowDelta -1;
                            realRotationsAmount -= m - rowDelta*2;

                            if(realRotationsAmount > 0){
                                newColumn -= realRotationsAmount;
                            }
                        }else{
                            newRow -= realRotationsAmount;
                        }
                    }else{
                        newColumn += realRotationsAmount;
                    }
                } else{
                    newRow += realRotationsAmount;
                }

                int matrixElement = scanner.nextInt();

                resultMatrix[newRow][newColumn] = matrixElement;
            }
        }

        /*
        1 8 7
        2   6
        3 4 5
        3x3 matrix -> 8 unique places -> 7 unique rotations
         */
    }
        /*
        00 01 02 03 04 05
        10 11 12 13 14 15
        20 21 22 23 24 25
        30 31 32 33 34 35
        40 41 42 43 44 45
         */
}
