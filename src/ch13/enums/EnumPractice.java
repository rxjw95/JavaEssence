package ch13.enums;

import java.util.Arrays;

public class EnumPractice {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(PATH.values()));
        System.out.println(Arrays.toString(Arrays.stream(PATH.values()).map(PATH::getUrl).toArray(String[]::new)));
    }
}

enum PATH {
    NAVER("https://www.naver.com"),
    GOOGLE("https://www.goggle.com"),
    DAUM("https://www.daum.net");

    private final String url;

    PATH(String url) {
        this.url = url;
    }

    String getUrl() {
        return url;
    }

}
