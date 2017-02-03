package com.minkov.superheroes;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.minkov.superheroes.base.ICanNavigateWith;
import com.minkov.superheroes.models.Superhero;
import com.minkov.superheroes.superheroes.details.SuperheroDetailsActivity;
import com.minkov.superheroes.tabs.AboutFragment;
import com.minkov.superheroes.tabs.FactionsListFragment;
import com.minkov.superheroes.tabs.SearchFragment;
import com.minkov.superheroes.tabs.SuperheroesListFragment;
import com.minkov.superheroes.tasks.HttpAsyncTask;
import com.minkov.superheroes.tasks.HttpPostAsyncTask;

public class MainActivity extends AppCompatActivity implements ICanNavigateWith<Superhero> {

    private TabsAdapter tabsAdapter;

    private ViewPager tabsContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabsAdapter = new TabsAdapter(getSupportFragmentManager());

        tabsContent = (ViewPager) findViewById(R.id.container);
        tabsContent.setAdapter(tabsAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(tabsContent);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        final Context ctx = this;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialDialog dialog =
                        new MaterialDialog.Builder(ctx)
                                .title("Add Superhero")
                                .customView(R.layout.modal_add_superhero, true)
                                .positiveText("Save")
                                .negativeText("Cancel")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        View modal = dialog.getCustomView();

                                        String name = ((EditText) modal.findViewById(R.id.etSuperheroName))
                                                .getText().toString();

                                        String secretIdentity = ((EditText) modal.findViewById(R.id.etSuperheroSecretIdentity))
                                                .getText().toString();

                                        Superhero superhero = new Superhero(name, secretIdentity);
                                        createSuperhero(superhero);
                                    }
                                })
                                .build();

                dialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void navigateWith(Superhero superhero) {
        Intent intent = new Intent(this, SuperheroDetailsActivity.class);
        intent.putExtra(SuperheroDetailsActivity.SUPERHERO_INTENT_KEY, superhero);
        this.startActivity(intent);
    }

    public class TabsAdapter extends FragmentStatePagerAdapter {

        public TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new SuperheroesListFragment();
                case 1:
                    return new FactionsListFragment();
                case 2:
                    return new SearchFragment();
                case 3:
                    return new AboutFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Heroes";
                case 1:
                    return "Factions";
                case 2:
                    return "Search";
                case 3:
                    return "About";
                default:
                    return "";
            }
        }
    }

    public void createSuperhero(Superhero superhero) {
        String url = ((SuperheroesApplication) this.getApplication()).getApiBaseUrl()
                + "superheroes";
        new HttpPostAsyncTask<>(superhero, Superhero.class, new HttpAsyncTask.OnDataReady<Superhero>() {
            @Override
            public void onReady(Superhero data) {
                //  hack to reload the activity
                finish();
                startActivity(getIntent());
            }
        }).execute(url);
    }
}
