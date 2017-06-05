package io.beering.beering;

/**
 * Created by hsun on 2017. 6. 5..
 */

public class Pub {

    String pubKorName;
    String pubEngName;

    String description;

    String hour;
    String pubTel;
    String pubLocation;

    String pubImage;

    public void setPubKorName(String korname) {
        this.pubKorName = korname;
    }

    public void setPubEngName(String engname) {
        this.pubEngName = engname;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setPubTel(String hour) {
        this.hour = hour;
    }

    public void setPubLocation(String location) {
        this.pubLocation = location;
    }

    public void setPubImage(String image) {
        this.pubImage = image;
    }

    public String getPubKorName() {
        return pubKorName;
    }

    public String getPubEngName() {
        return pubEngName;
    }

    public String getDescription() {
        return description;
    }

    public String getHour() {
        return hour;
    }

    public String getPubTel() {
        return pubTel;
    }

    public String getPubLocation() {
        return pubLocation;
    }

    public String getPubImage() {
        return pubImage;
    }
}
