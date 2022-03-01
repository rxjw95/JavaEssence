package ch13.enums;

import java.util.Arrays;
import java.util.List;

public class EnumPractice {
    public static void main(String[] args) {
        System.out.println(PATH.GOOGLE.getValues()); // [https://www.goggle.com, 구글]
    }
}

enum PATH {
    NAVER("https://www.naver.com", "네이버"),
    GOOGLE("https://www.goggle.com", "구글"),
    DAUM("https://www.daum.net", "다음");

    private final String url;
    private final String koreanDomain;

    PATH(String url, String koreanDomain) {
        this.url = url;
        this.koreanDomain = koreanDomain;
    }

    String getUrl() {
        return url;
    }

    String getKoreanDomain() {
        return koreanDomain;
    }

    List<String> getValues() {
        return List.of(url, koreanDomain);
    }

}
