package com.example.owner.weatherinfo.data;

/**
 * Created by owner on 11/25/17.
 */

public class City {

    private String cityTitle;

    public City(){

    }

    public City(String cityTitle){
        this.cityTitle = cityTitle;
    }

    public String getCityTitle() {
        return cityTitle;
    }

    public void setCityTitle(String cityTitle) {
        this.cityTitle = cityTitle;
    }

}
