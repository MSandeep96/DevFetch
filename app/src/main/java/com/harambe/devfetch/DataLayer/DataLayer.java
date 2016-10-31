package com.harambe.devfetch.DataLayer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.harambe.devfetch.ImpConstants;
import com.harambe.devfetch.NetworkPojos.DevRantResponse;
import com.harambe.devfetch.NetworkPojos.Rants;
import com.harambe.devfetch.mainActivity_MVP.MainPresenter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sandeep on 01-11-2016.
 */

public class DataLayer implements DataLayerInterface,ImpConstants {

    private final DevRantAPI mDevRantApi;

    public DataLayer(){
        Gson gson=new GsonBuilder().create();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://www.devrant.io/api/devrant/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mDevRantApi=retrofit.create(DevRantAPI.class);
    }


    @Override
    public void getHomeFeed(final MainPresenter mainPresenter) {
        Call<DevRantResponse> mResp=mDevRantApi.getRants(0,PAGIN_LIMIT);
        mResp.enqueue(new Callback<DevRantResponse>() {
            @Override
            public void onResponse(Call<DevRantResponse> call, Response<DevRantResponse> response) {
                if(response.body().isSuccess()){
                    ArrayList<Rants> mRants=new ArrayList<Rants>(response.body().getRants());
                    mainPresenter.gotHomeFeed(mRants);
                }
            }

            @Override
            public void onFailure(Call<DevRantResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void getPaginatedTracks(int presentCount, final MainPresenter mainPresenter) {
        Call<DevRantResponse> mResp=mDevRantApi.getRants(0,PAGIN_LIMIT);
        mResp.enqueue(new Callback<DevRantResponse>() {
            @Override
            public void onResponse(Call<DevRantResponse> call, Response<DevRantResponse> response) {
                if(response.body().isSuccess()){
                    ArrayList<Rants> mRants=new ArrayList<Rants>(response.body().getRants());
                    mainPresenter.gotPaginatedTracks(mRants);
                }
            }

            @Override
            public void onFailure(Call<DevRantResponse> call, Throwable t) {

            }
        });
    }
}
