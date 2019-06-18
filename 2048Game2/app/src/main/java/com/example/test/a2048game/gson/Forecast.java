package com.example.test.a2048game.gson;

import com.google.gson.annotations.SerializedName;

public class Forecast {

    //未来天气情况
    public String date;

    @SerializedName("tmp")
    public Temperature temperature;

    @SerializedName("cond")
    public More more;

    public class Temperature {

        public String max;

        public String min;

    }

    public class More {

        @SerializedName("txt_d")
        public String info;

    }

}
