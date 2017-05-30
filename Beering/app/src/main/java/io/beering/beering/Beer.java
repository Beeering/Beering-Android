package io.beering.beering;

/**
 * Created by hsun on 2017. 5. 16..
 */

public class Beer {

    private String beerName;
    private String beerStyle;
    private String beerNation;
    private int beerImage;


    public void setBeerName(String name) {
        this.beerName = name;
    }

    public void setBeerStyle(String style) {
        this.beerStyle = style;
    }

    public void setBeerNation(String nation) {
        this.beerNation = nation;
    }

    public void setBeerImage(int image) {
        this.beerImage = image;
    }

    public String getBeerName() {
        return beerName;
    }

    public String getBeerStyle() {
        return beerStyle;
    }

    public String getBeerNation() {
        return beerNation;
    }

    public int getBeerImage() {
        return beerImage;
    }
}
