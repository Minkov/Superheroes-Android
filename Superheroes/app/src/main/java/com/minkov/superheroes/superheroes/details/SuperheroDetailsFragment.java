package com.minkov.superheroes.superheroes.details;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minkov.superheroes.R;
import com.minkov.superheroes.models.Superhero;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuperheroDetailsFragment extends Fragment {
    private Superhero superhero;

    public SuperheroDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_superheroes_details, container, false);

        ((TextView) root.findViewById(R.id.tvName))
                .setText(this.superhero.getName());

        ((TextView) root.findViewById(R.id.tvSecretIdentity))
                .setText(this.superhero.getSecretIdentity());

        return root;
    }

    public static SuperheroDetailsFragment create(Superhero superhero) {
        SuperheroDetailsFragment fragment = new SuperheroDetailsFragment();
        fragment.setSuperhero(superhero);
        return fragment;
    }

    public void setSuperhero(Superhero superhero) {
        this.superhero = superhero;
    }
}
