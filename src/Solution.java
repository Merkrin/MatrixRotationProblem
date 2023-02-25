import java.util.Scanner;

public class Solution {
    private final static Scanner scanner = new Scanner(System.in);

    private static int m;
    private static int n;
    private static int r;

    public static void main(String[] args) {
        m = scanner.nextInt(); // rows amount
        n = scanner.nextInt(); // columns amount
        r = scanner.nextInt(); // rotations amount

        int elementsAmount = m *n;

        int[] matrix = new int[elementsAmount+1];

        for(int row = 0; row < m; row++){
            for(int column = 0; column < n; column++){
                int rowDelta = Math.min(m - row - 1, row);
                int columnDelta = Math.min(n - column - 1, column);
                int squareDelta = Math.min(rowDelta, columnDelta);

                int newRow = row;
                int newColumn = column;

                int uniqueRotationsAmount = 2*(n-2*squareDelta)+2*(m-2*squareDelta);
                if(n > 2 && m > 2){
                    uniqueRotationsAmount -= 4;
                }

                int realRotationsAmount = r % uniqueRotationsAmount;

                for (int k = 0; k < realRotationsAmount; k++) {
                    if (newRow == squareDelta) {
                        if (newColumn > squareDelta ) {
                            newColumn -= 1;
                        } else {
                            newRow += 1;
                        }
                    } else if (newRow == m - squareDelta - 1) {
                        if (newColumn < n - squareDelta - 1) {
                            newColumn += 1;
                        } else {
                            newRow -= 1;
                        }
                    } else if (newColumn == squareDelta) {
                        if (newRow < m - 1) {
                            newRow += 1;
                        } else {
                            newColumn += 1;
                        }
                    } else if (newColumn == n - squareDelta - 1) {
                        if (newRow > 0) {
                            newRow -= 1;
                        } else {
                            newColumn -= 1;
                        }
                    }
                }

                matrix[newRow * n + newColumn] = scanner.nextInt();
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i * n + j] + " ");
            }
            System.out.println();
        }
    }
}