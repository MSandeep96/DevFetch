package com.harambe.devfetch.mainActivity_MVP.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.harambe.devfetch.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RantsViewFragment extends Fragment implements RantsView {


    public RantsViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rants_view, container, false);
    }

}
