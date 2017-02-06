package com.minkov.superheroes.superheroes.list;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.minkov.superheroes.base.ICanNavigateWith;
import com.minkov.superheroes.tasks.DownloadImgAsyncTask;
import com.minkov.superheroes.ui.LoadingFragment;
import com.minkov.superheroes.R;
import com.minkov.superheroes.SuperheroesApplication;
import com.minkov.superheroes.adapters.SuperheroesAdapter;
import com.minkov.superheroes.models.Superhero;
import com.minkov.superheroes.tasks.HttpGetAsyncTask;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuperheroesListFragment extends Fragment {
    private ListView lvSuperheroes;
    private ArrayList<Superhero> superheroes;
    private SuperheroesAdapter superheroesAdapter;
    private LoadingFragment loading;
    private String pattern;

    public SuperheroesListFragment() {
        // Required empty public constructor
    }

    public static SuperheroesListFragment create() {
        SuperheroesListFragment fragment = new SuperheroesListFragment();
        fragment.setPattern("");
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_superheroes_list, container, false);

        this.superheroesAdapter = new SuperheroesAdapter(this.getContext(), new ArrayList<Superhero>());

        this.lvSuperheroes = (ListView) root.findViewById(R.id.lvSuperheroes);
        this.lvSuperheroes.setAdapter(this.superheroesAdapter);

        this.lvSuperheroes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (getActivity() instanceof ICanNavigateWith) {
                    Superhero superhero = superheroes.get(position);
                    ((ICanNavigateWith<Superhero>) getActivity())
                            .navigateWith(superhero);
                }
            }
        });
        this.loading = LoadingFragment.create(this.getContext());

        this.load();

        return root;
    }

    private void load() {
        this.loading.show();
        String apiUrl = ((SuperheroesApplication) this.getActivity().getApplication())
                .getApiBaseUrl() + "superheroes?pattern=" + this.getPattern();

        new HttpGetAsyncTask<>(Superhero[].class, new HttpGetAsyncTask.OnDataReady<Superhero[]>() {
            @Override
            public void onReady(Superhero[] items) {
                superheroes = new ArrayList<>(Arrays.asList(items));

                getActivity()
                        .runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                superheroesAdapter.addAll(superheroes);
                                loading.hide();
                            }
                        });
            }
        }).execute(apiUrl);
    }

    public void addSuperhero(Superhero data) {
        this.superheroesAdapter.add(data);
    }

    public void setPattern(String pattern) {
        this.setPattern(pattern, false);
    }

    public void setPattern(String pattern, boolean update) {
        this.pattern = pattern;
        if (update) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    superheroesAdapter.clear();
                    load();
                }
            });
        }
    }

    public String getPattern() {
        return pattern;
    }
}
