package com.example.test.a2048game.gson;

import com.google.gson.annotations.SerializedName;

public class Basic {

    @SerializedName("city")//JSON字段和Java字段建立映射
    public String cityName;

    @SerializedName("id") //城市名
    public String weatherId;

    public Update update;//天气情况

    public class Update {

        @SerializedName("loc")
        public String updateTime;//天气更新时间

    }

}
