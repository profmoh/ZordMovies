package com.movieapp.datazord.zordmovies.pager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movieapp.datazord.zordmovies.R;

/**
 * Created by MFawzy on 10/21/2016.
 */
public class fragment_pager_three extends Fragment{

    public fragment_pager_three() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pager_three, container, false);
    }

}