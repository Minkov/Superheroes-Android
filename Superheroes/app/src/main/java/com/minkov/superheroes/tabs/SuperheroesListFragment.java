package com.minkov.superheroes.tabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.minkov.superheroes.base.ICanNavigateWith;
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
    private String apiUrl;
    private ListView lvSuperheroes;
    private ArrayList<Superhero> superheroes;
    private SuperheroesAdapter superheroesAdapter;
    private LoadingFragment loading;

    public SuperheroesListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_superheroes_list, container, false);


        this.apiUrl = ((SuperheroesApplication) this.getActivity().getApplication())
                .getApiBaseUrl() + "superheroes";

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
        }).execute(this.apiUrl);
    }

    public void addSuperhero(Superhero data) {
        this.superheroesAdapter.add(data);
    }
}
