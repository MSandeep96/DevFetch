package com.harambe.devfetch;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.harambe.devfetch.NetworkPojos.Rants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sandeep on 01-11-2016.
 */

public class RantsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int LOADING_VIEW = 3;
    private static final int TRACK_VIEW = 6;
    public ArrayList<Rants> mRants;

    //tracks if pagination is enable and adds prog bar at bottom accordingly
    boolean isPagin = true;

    public void setPagin(boolean pagin) {
        isPagin = pagin;
    }

    public RantsAdapter() {

    }

    @Override
    public int getItemViewType(int position) {
        if (position == mRants.size()) {
            return LOADING_VIEW;
        } else {
            return TRACK_VIEW;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TRACK_VIEW) {

            //Inflate our layout
            View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rant_item,parent,false);
            return new RantsViewHolder(mView);
        }else{

            //Inflate a progress view at bottom
            LinearLayout mLinearLayout = new LinearLayout(parent.getContext());
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mLinearLayout.setLayoutParams(params);
            mLinearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
            ProgressBar mProgre = new ProgressBar(parent.getContext());
            mLinearLayout.addView(mProgre);
            return new RecyclerView.ViewHolder(mLinearLayout) {};
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof RantsViewHolder){
            ((RantsViewHolder) holder).mRantTextView.setText(mRants.get(position).getText());
        }
    }

    @Override
    public int getItemCount() {
        if(mRants==null){
            return 0;
        }
        return mRants.size() + 1;
    }

    /**
     * Check if theres a difference and then add them
     * Case 1: Empty list, add directly
     * Case 2: Compare first pagin_limit tracks and new items
     * Case 3: No diff
     * Case 4: Smaller adapters buffer than received buffer
     * Only for fetching latest rants
     *
     * @param rants to be added
     */
    public void addItems(ArrayList<Rants> rants) {
        //case 1
        if (mRants == null) {
            mRants = rants;
            notifyItemRangeInserted(0, rants.size());
            return;
        }

        if(mRants.size()==0){
            mRants.addAll(rants);
            notifyItemRangeInserted(0,rants.size());
            return;
        }
        //case 4
        if (rants.size() > mRants.size()) {
            mRants = rants;
            notifyItemRangeInserted(0, rants.size() - mRants.size());
            return;
        }
        //case 2
        ArrayList<Rants> mTemp = new ArrayList<>(mRants.subList(0, rants.size()));
        mTemp.removeAll(rants);
        if (mTemp.size() != 0) {
            mRants.addAll(0, mTemp);
            notifyItemRangeInserted(0, mTemp.size());
        }
    }

    /**
     * Add paged rants to the bottom
     *
     * @param rants Rants returned from paging call
     */
    public void addPaginItems(ArrayList<Rants> rants) {
        int prevSize = mRants.size();
        mRants.addAll(rants);
        notifyItemRangeInserted(prevSize, rants.size());
    }

    public static class RantsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ri_tv_rant_text)
        TextView mRantTextView;

        public RantsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
