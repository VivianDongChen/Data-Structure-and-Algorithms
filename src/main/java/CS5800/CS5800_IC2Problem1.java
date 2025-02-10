package CS5800;

import java.io.*;
import java.util.*;
import java.text.*;
import java.util.regex.*;

public class CS5800_IC2Problem1 {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        // Create a scanner to read input
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the size of the array:");
        int n = scanner.nextInt(); // Read the size of the array
        int[] arr = new int[n];

        System.out.println("Enter the array elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt(); // Read the array elements
        }

        CS5800_IC2Problem1 solution = new CS5800_IC2Problem1();
        int result = solution.min_sum(arr);

        System.out.println("Minimum weighted sum is: " + result);

        // Close the scanner
        scanner.close();
    }

    public int min_sum(int[] arr){
        Arrays.sort(arr);
        int sum = 0;
        for(int i = arr.length - 1; i >= 0; i--){
            sum += arr[i] * (arr.length - i - 1);
        }
        return sum;
    }
}