package ch7.OOP2;

import java.util.Properties;

public class ObjectOrientedChapter2 {
    public static void main(String[] args) {
        CaptionTv captionTv = new CaptionTv();
        Properties hash = new Properties();
        hash.put("jangwook", "2");
        System.out.println(hash.getProperty("jangwook"));

    }
}

class Tv {
    boolean power;
    int channel;

    public void power() {
        this.power = !this.power;
    }

    public void channelUp() {
        this.channel ++;
    }

    public void channelDown() {
        if(this.channel <= 1) return;
        this.channel --;
    }
}

class CaptionTv extends Tv {
    boolean caption;

    public void displayCaption() {
        this.caption = !this.caption;
    }
}
