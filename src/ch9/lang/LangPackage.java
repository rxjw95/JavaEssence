package ch9.lang;

public class LangPackage {
    public static void main(String[] args) {
        stringBufferTest();
        objectClassTest();
    }

    private static void objectClassTest() {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(1, 2);

        System.out.println(p1.equals(p2)); // false

        p2 = p1;

        System.out.println(p1.equals(p2)); // true
    }

    private static void stringBufferTest() {
        StringBuffer sb = new StringBuffer("abc");
        StringBuffer sb2 = new StringBuffer("abc");

        System.out.println(sb.equals(sb2)); // false
        System.out.println(sb.toString().equals(sb2.toString())); // true
    }
}

class Point {
    int x, y;

    public Point(int _x, int _y) {
        x = _x;
        y = _y;
    }
}
