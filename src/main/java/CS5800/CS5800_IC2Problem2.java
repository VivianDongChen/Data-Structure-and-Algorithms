package CS5800;

import java.util.*;
import java.util.regex.*;

public class CS5800_IC2Problem2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Safely parse n and k
        int n = parseInteger(scanner.nextLine(), "n");
        int k = parseInteger(scanner.nextLine(), "k");

        // Safely parse arrays
        String inputArrA = scanner.nextLine();
        String inputArrB = scanner.nextLine();

        int[] arrA = parseInputArray(inputArrA, "arrA");
        int[] arrB = parseInputArray(inputArrB, "arrB");

        // Call the max_sum function
        CS5800_IC2Problem2 solution = new CS5800_IC2Problem2();
        int[] result = solution.max_sum(n, k, arrA, arrB);

        // Print the result
        System.out.print("arrC = [");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
            if (i < result.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    // Helper method to parse integer input
    public static int parseInteger(String input, String prefix) {
        try {
            return Integer.parseInt(input.split(" = ")[1].trim());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid input for " + prefix + ": " + input);
        }
    }

    // Helper method to parse array input
    public static int[] parseInputArray(String input, String prefix) {
        // Use regex to extract the array content between brackets
        Pattern pattern = Pattern.compile("\\[([^]]+)\\]");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String arrayContent = matcher.group(1);
            String[] elements = arrayContent.split(",\\s*");
            int[] result = new int[elements.length];
            for (int i = 0; i < elements.length; i++) {
                result[i] = Integer.parseInt(elements[i]);
            }
            return result;
        } else {
            throw new IllegalArgumentException("Invalid array input for " + prefix + ": " + input);
        }
    }

    public int[] max_sum(int n, int k, int[] a, int[] b){
        int[] res = new int[k];
        Arrays.sort(a);
        Arrays.sort(b);

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((x, y) -> y[0] - x[0]);

        for(int j = 0; j < n; j++){
            maxHeap.offer(new int[]{a[n-1]+b[j], n-1,j});
        }

        int index = 0;

        while(!maxHeap.isEmpty() && k > 0){
            int[] poll = maxHeap.poll();
            res[index++]=(poll[0]);
            int i = poll[1];
            int j = poll[2];
            k--;
            if(i > 0){
                maxHeap.offer(new int[]{a[i-1]+b[j], i-1, j});
            }
        }

        return res;

    }
}