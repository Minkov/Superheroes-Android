package com.minkov.superheroes.tabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minkov.superheroes.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {


    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_about, container, false);

        ((TextView)root.findViewById(R.id.tvAppName)).setText("The Superheroes");
        ((TextView)root.findViewById(R.id.tvAppCreator)).setText("Doncho Minkov");
        ((TextView)root.findViewById(R.id.tvAppDesc)).setText("Application about superheroes and their factions");

        return root;
    }

}
