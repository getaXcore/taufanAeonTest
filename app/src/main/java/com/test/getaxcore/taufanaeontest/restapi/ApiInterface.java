package com.test.getaxcore.taufanaeontest.restapi;

import com.test.getaxcore.taufanaeontest.model.Photos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by 001910947 on 7/31/2020.
 */

public interface ApiInterface {
    @GET("/photos")
    Call<List<Photos>> getResult();
}
