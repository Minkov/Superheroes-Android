package com.minkov.superheroes.superheroes.details;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.minkov.superheroes.R;
import com.minkov.superheroes.models.Superhero;

public class SuperheroDetailsActivity extends AppCompatActivity {

    public static final String SUPERHERO_INTENT_KEY = "superhero_details_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superhero_details);

        Superhero superhero = (Superhero) this.getIntent().getSerializableExtra(SUPERHERO_INTENT_KEY);

        Fragment fragment = SuperheroDetailsFragment.create(superhero);

        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_superhero_details, fragment)
                .commit();
    }
}
