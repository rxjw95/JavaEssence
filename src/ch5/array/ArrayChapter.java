package ch5.array;

import java.util.Arrays;

public class ArrayChapter {
    public static void main(String[] args) {
        int[] a = new int[] {1, 2, 3};
        int[] b = new int[5];
        b = a.clone();
        // System.arraycopy(a, 0 , b, 0, a.length);
        System.out.println(a == b);
    }
}
