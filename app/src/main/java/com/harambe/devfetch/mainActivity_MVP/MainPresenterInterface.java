package com.harambe.devfetch.mainActivity_MVP;

import com.harambe.devfetch.NetworkPojos.Rants;
import com.harambe.devfetch.mainActivity_MVP.Fragment.RantsView;

import java.util.ArrayList;

/**
 * Created by Sandeep on 01-11-2016.
 */

public interface MainPresenterInterface {
    void setRantsView(RantsView rantsViewFragment);

    void getHomeFeed();

    void gotHomeFeed(ArrayList<Rants> mRants);

    void getPaginatedTracks(int presentCount);

    void gotPaginatedTracks(ArrayList<Rants> mRants);
}
