package com.harambe.devfetch.DataLayer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sandeep on 01-11-2016.
 */

public class DataLayer implements DataLayerInterface {

    private final DevRantAPI mDevRantApi;

    public DataLayer(){
        Gson gson=new GsonBuilder().create();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://www.devrant.io/api/devrant/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mDevRantApi=retrofit.create(DevRantAPI.class);
    }


}
