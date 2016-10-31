package com.harambe.devfetch.mainActivity_MVP;

import android.os.Parcelable;

import com.harambe.devfetch.DataLayer.DataLayer;
import com.harambe.devfetch.NetworkPojos.Rants;
import com.harambe.devfetch.mainActivity_MVP.Fragment.RantsView;

import java.util.ArrayList;

/**
 * Created by Sandeep on 01-11-2016.
 */

public class MainPresenter implements MainPresenterInterface {

    private final DataLayer mDataLayer;
    MainView mView;
    private RantsView mRantsView;

    public MainPresenter(MainView mView) {
        this.mView = mView;
        mDataLayer= new DataLayer();
    }


    public void getHomeFeed() {
        mDataLayer.getHomeFeed(this);
    }

    @Override
    public void gotHomeFeed(ArrayList<Rants> mRants) {
        mRantsView.gotHomeFeed(mRants);
    }

    @Override
    public void getPaginatedTracks(int presentCount) {
        mDataLayer.getPaginatedTracks(presentCount,this);
    }

    @Override
    public void gotPaginatedTracks(ArrayList<Rants> mRants) {
        mRantsView.gotPaginatedTracks(mRants);
    }

    @Override
    public void notifyChange(int size) {
        mView.notifyChange(size);
    }

    @Override
    public ArrayList<? extends Parcelable> getFeedForBundle() {
        return mRantsView.getFeedForBundle();
    }

    @Override
    public void setHomeFeedFromBundle(ArrayList<Rants> parcelableArrayList) {
        mRantsView.setFeedFromBundle(parcelableArrayList);
    }

    @Override
    public void setRantsView(RantsView rantsViewFragment) {
        mRantsView=rantsViewFragment;
    }
}
