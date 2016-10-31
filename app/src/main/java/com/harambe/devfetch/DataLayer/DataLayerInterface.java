package com.harambe.devfetch.DataLayer;

import com.harambe.devfetch.mainActivity_MVP.MainPresenter;

/**
 * Created by Sandeep on 01-11-2016.
 */

public interface DataLayerInterface {
    void getHomeFeed(MainPresenter mainPresenter);

    void getPaginatedTracks(int presentCount, MainPresenter mainPresenter);
}
