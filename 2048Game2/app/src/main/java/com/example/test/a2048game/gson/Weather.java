package com.example.test.a2048game.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {

    //引用创建的各个实体类：
    public String status;//返回是否成功返回值

    public Basic basic;

    public AQI aqi;

    public Now now;

    public Suggestion suggestion;

    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;//解析出数组

}
