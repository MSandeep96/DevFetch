package com.harambe.devfetch.mainActivity_MVP.Fragment;

import com.harambe.devfetch.NetworkPojos.Rants;

import java.util.ArrayList;

/**
 * Created by Sandeep on 01-11-2016.
 */

public interface RantsView {
    void gotHomeFeed(ArrayList<Rants> mRants);

    void gotPaginatedTracks(ArrayList<Rants> mRants);
}
