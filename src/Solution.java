import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[] a = new int[] {1, 2, 3};
        int[] b = new int[5];

        System.arraycopy(a, 0 , b, 0, a.length);

        System.out.println(Arrays.toString(a)); // result: [1, 2, 3]
        System.out.println(Arrays.toString(b)); // result: [1, 2, 3, 0, 0]
    }
}
