package com.test.getaxcore.taufanaeontest.restapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 001910947 on 7/31/2020.
 */

public class ApiClient {
    public static final String BASE_URL="https://jsonplaceholder.typicode.com";
    public static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
