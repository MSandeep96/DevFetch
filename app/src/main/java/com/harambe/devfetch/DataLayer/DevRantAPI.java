package com.harambe.devfetch.DataLayer;

import com.harambe.devfetch.NetworkPojos.DevRantResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sandeep on 01-11-2016.
 */

public interface DevRantAPI {

    @GET("rants?app=3")
    Call<DevRantResponse> getRants(@Query("skip") int no,@Query("limit") int limit);

    @GET("search?app=3")
    Call<DevRantResponse> getSearchResults(@Query("term") String term);
}
