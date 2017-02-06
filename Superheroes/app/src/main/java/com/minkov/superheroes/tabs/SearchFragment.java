package com.minkov.superheroes.tabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.astuetz.PagerSlidingTabStrip;
import com.minkov.superheroes.R;
import com.minkov.superheroes.search.SearchResultFragment;
import com.minkov.superheroes.superheroes.list.SuperheroesListFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);

        this.setupTabs(root);

        return root;
    }

    private void setupTabs(View root) {
        ViewPager pager = (ViewPager) root.findViewById(R.id.vpSearchResult);

        SearchFragmentsAdapter adapter = new SearchFragmentsAdapter(this.getFragmentManager());

        pager.setAdapter(adapter);


        EditText etPattern = (EditText)root.findViewById(R.id.etSearchPattern);

        etPattern.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                superheroesListFragment.setPattern(s.toString(), true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) root.findViewById(R.id.tabs);
        tabs.setViewPager(pager);
    }

    private SuperheroesListFragment superheroesListFragment;

    public class SearchFragmentsAdapter extends FragmentStatePagerAdapter {

        private String[] titles = {"Superheroes", "Factions", "Powers"};

        public SearchFragmentsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    superheroesListFragment = SuperheroesListFragment.create();
                    return superheroesListFragment;
                default:
                    return new SearchResultFragment();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return this.titles[position];
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
