package com.harambe.devfetch.mainActivity_MVP.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.harambe.devfetch.ImpConstants;
import com.harambe.devfetch.NetworkPojos.Rants;
import com.harambe.devfetch.R;
import com.harambe.devfetch.RantsAdapter;
import com.harambe.devfetch.RecyclerViewDecorations.TracksItemDecoration;
import com.harambe.devfetch.mainActivity_MVP.MainPresenterInterface;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RantsViewFragment extends Fragment implements RantsView,ImpConstants {


    @BindView(R.id.frv_prog_bar)
    ProgressBar mProgBar;
    @BindView(R.id.frv_recy_view)
    RecyclerView mRecyView;

    MainPresenterInterface mPresenter;
    private LinearLayoutManager mLLM;

    RantsAdapter mAdapter;

    //flags for maintaining pagination
    boolean isScrollable= true;
    boolean isFetching = false;
    boolean gotFirstResp=false;

    //keeps count of present objects for pagination
    int presentCount=0;

    //for real time updation
    private Handler mHandler;
    private Runnable mFetchThread;
    long delayInMills;

    public RantsViewFragment() {
        // Required empty public constructor
    }


    //detects pagination
    RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if(isScrollable){
                if(dy>0){
                    int visibleItemCount = mLLM.getChildCount();
                    int totalItemCount = mLLM.getItemCount();
                    int pastVisibleItems = mLLM.findFirstVisibleItemPosition();
                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        if (!isFetching) {
                            isFetching = true;
                            mPresenter.getPaginatedTracks(presentCount);
                        }
                    }
                }
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_rants_view, container, false);
        ButterKnife.bind(this,mView);

        //get presenter
        mPresenter=((CommunicatorInterface)getContext()).getPresenter();

        //add this to presenter
        mPresenter.setRantsView(this);

        mLLM = new LinearLayoutManager(getContext());
        mRecyView.setLayoutManager(mLLM);
        mRecyView.addItemDecoration(new TracksItemDecoration(getContext()));
        mAdapter = new RantsAdapter(mPresenter);
        mRecyView.setAdapter(mAdapter);
        mRecyView.addOnScrollListener(mScrollListener);
        mHandler = new Handler();
        //fetch items
        delayInMills= Prefs.getLong(TIME_DELAY,DEFAULT_TIME_DELAY);
        if(savedInstanceState!=null){
            mProgBar.setVisibility(View.GONE);
        }else{
            startFetchingItems();
        }
        return mView;
    }

    /**
     * A handler to keep fetching items in a certain interval
     */
    private void startFetchingItems() {
        mFetchThread = new Runnable() {
            @Override
            public void run() {

                mPresenter.getHomeFeed();
                //fetches every
                mHandler.postDelayed(mFetchThread,delayInMills);
            }
        };
        mFetchThread.run();
    }


    /**
     * Called when new rants call returns
     * @param mRants Rants retrieved
     */
    @Override
    public void gotHomeFeed(ArrayList<Rants> mRants) {
        if(mProgBar.isShown()){
            mProgBar.setVisibility(View.INVISIBLE);
        }
        if(!gotFirstResp){
            presentCount+=mRants.size();
            gotFirstResp=true;
        }
        mAdapter.addItems(mRants);
    }

    /**
     * Called when paginated rants return
     * @param mRants paginated rants
     */
    @Override
    public void gotPaginatedTracks(ArrayList<Rants> mRants) {
        isFetching=false;
        if(mRants.size()< PAGIN_LIMIT){
            isScrollable=false;
            if(mRants.size()==0)
                return;
        }
        presentCount+=mRants.size();
        mAdapter.addPaginItems(mRants);
    }

    @Override
    public ArrayList<? extends Parcelable> getFeedForBundle() {
        mHandler.removeCallbacks(mFetchThread);
        return mAdapter.getFeedForBundle();
    }

    @Override
    public void setFeedFromBundle(ArrayList<Rants> parcelableArrayList) {
        if(!gotFirstResp){
            presentCount+=parcelableArrayList.size();
            gotFirstResp=true;
        }
        mAdapter.setFeedFromBundle(parcelableArrayList);
        startFetchingItems();
    }
}
