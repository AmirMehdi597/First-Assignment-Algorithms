import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercises {

    public int[] productIndices(int[] values, int target) {
        for (int i = 0; i < values.length; i++) {
            for (int j = i + 1; j < values.length; j++) {
                if (values[i] * values[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /*
        given a matrix of random integers, you should do spiral traversal in it
        e.g. if the matrix is as shown below:
        1 2 3
        4 5 6
        7 8 9
        then the spiral traversal of that is:
        {1, 2, 3, 6, 9, 8, 7, 4, 5}

        so you should walk in that matrix in a curl and then add the numbers in order you've seen them in a 1D array
    */
    public int[] spiralTraversal(int[][] values, int rows, int cols) {
        int[] result = new int[rows * cols];
        int index = 0;
        int top = 0, bottom = rows - 1, left = 0, right = cols - 1;

        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) {
                result[index++] = values[top][i];
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                result[index++] = values[i][right];
            }
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result[index++] = values[bottom][i];
                }
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result[index++] = values[i][left];
                }
                left++;
            }
        }

        return result;
    }

    /*
        integer partitioning is a combinatorics problem in discreet maths
        the problem is to generate sum numbers which their summation is the input number

        e.g. 1 -> all partitions of integer 3 are:
        3
        2, 1
        1, 1, 1

        e.g. 2 -> for number 4 goes as:
        4
        3, 1
        2, 2
        2, 1, 1
        1, 1, 1, 1

        note: as you can see in examples, we want to generate distinct summations, which means 1, 2 and 2, 1 are no different
        you should generate all partitions of the input number and

        hint: you can measure the size and order of arrays by finding the pattern of partitions and their number
        trust me, that one's fun and easy :)

        if you're familiar with lists and arraylists, you can also edit method's body to use them instead of array
    */
    public int[][] intPartitions(int n) {
        List<int[]> partitions = new ArrayList<>();
        int[] p = new int[n];
        int k = 0;
        p[k] = n;

        while (true) {
            int[] partition = new int[k + 1];
            System.arraycopy(p, 0, partition, 0, k + 1);
            partitions.add(partition);
            int a = 0;
            while (k >= 0 && p[k] == 1) {
                a += p[k];
                k--;
            }

            if (k < 0) {
                break;
            }

            p[k]--;
            a++;

            while (a > p[k]) {
                p[k + 1] = p[k];
                a = a - p[k];
                k++;
            }

            p[k + 1] = a;
            k++;
        }
        int[][] result = new int[partitions.size()][];
        for (int i = 0; i < partitions.size(); i++) {
            result[i] = partitions.get(i);
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] a = new int[4];
        for (int i = 0; i < 4; i++) {
            a[i] = sc.nextInt();
        }
        int b = sc.nextInt();
        Exercises e = new Exercises();
        int[] indices = e.productIndices(a, b);
        if (indices != null) {
            System.out.println(indices[0] + " " + indices[1]);
        } else {
            System.out.println("No such pair found.");
        }
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        int[][] m = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m[i][j] = sc.nextInt();
            }
        }
        int[] s = e.spiralTraversal(m, rows, cols);
        for (int num : s) {
            System.out.print(num + " ");
        }
        System.out.println();
        int n = sc.nextInt();
        int[][] partitions = e.intPartitions(n);
        for (int[] partition : partitions) {
            for (int num : partition) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
//every thing is ok


