package ch5.array;

import java.util.Arrays;

public class ArrayChapter {
    public static void main(String[] args) {
        int[] src = {1, 2, 3};
        int[] des = new int[5];

        System.arraycopy(src, 0 , des, 0, src.length);

        System.out.println(Arrays.toString(src)); // [1, 2, 3]
        System.out.println(Arrays.toString(des)); // [1, 2, 3, 0, 0]
    }
}
