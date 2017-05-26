package com.example.uzezi.activitieswithintents.fragment_package;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uzezi.activitieswithintents.R;

/**
 * Created by Uzezi on 5/26/2017.
 */

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_gmaps, container, false);
        return rootView;
    }
}
