package com.example.owner.weatherinfo.network;

import com.example.owner.weatherinfo.data.WeatherResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by owner on 11/25/17.
 */

public interface CityAPI {

    @GET("data/2.5/weather")
    Call<WeatherResult> getCityWeather(
            @Query("q") String base,
            @Query("units") String units,
            @Query("APPID") String appid);
}
