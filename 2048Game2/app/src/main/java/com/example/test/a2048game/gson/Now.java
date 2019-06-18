package com.example.test.a2048game.gson;

import com.google.gson.annotations.SerializedName;

public class Now {

    //显示温度和天气情况
    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More {

        @SerializedName("txt")
        public String info;

    }

}
