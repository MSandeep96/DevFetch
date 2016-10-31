package com.harambe.devfetch.DataLayer;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sandeep on 01-11-2016.
 */

public interface DevRantAPI {

    @GET("/rants?app=3&limit=30")
    void getRants(@Query("skip") int no);

    @GET("/search?app=3")
    void getSearchResults(@Query("term") String term);
}
