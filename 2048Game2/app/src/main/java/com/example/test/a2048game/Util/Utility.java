package com.example.test.a2048game.Util;

import android.text.TextUtils;

import com.example.test.a2048game.db.City;
import com.example.test.a2048game.db.Country;
import com.example.test.a2048game.db.Province;
import com.example.test.a2048game.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 陈芳微 on 2019/6/13.
 */

public class Utility {
    /**
     * 因为我们的服务器是json格式的所以我们需要一个进行解析处理
     * 解析和处理服务器返回的省级数据
     */
    /*handleProvincesResponse()、handleCitiesResponse()、handleCountiesResponse()
    分别用于解析和处理服务器返回的省级、市级和县级数据。处理的方式都是类似的，先使
    用JSONArray和JSONObject将数据解析出来，然后组装成实体类对象，再调用save()方法
    将数据存储到数据库当中。*/
    public static boolean handleProvinceResponse(String response) {
        /*如果我们传入进来的字符不为空*/
        if (!TextUtils.isEmpty(response)) {
            try {
                //定义一个JSON数组，用于将服务器返回的数据传入到一个JSONArray对象中；
                //然后循环遍历这个JSONArray，从中取出每一个元素（JSONObject对象），
                // 接下来只需调用getString()方法即可将数据取出。
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountyResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    Country county = new Country();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 将返回的JSON数据解析成Weather实体类
     */
    public static Weather handleWeatherResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, Weather.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}