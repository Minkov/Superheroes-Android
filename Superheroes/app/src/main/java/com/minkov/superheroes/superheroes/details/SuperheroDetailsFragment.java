package com.minkov.superheroes.superheroes.details;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.minkov.superheroes.R;
import com.minkov.superheroes.SuperheroesApplication;
import com.minkov.superheroes.adapters.SuperheroesAdapter;
import com.minkov.superheroes.models.Superhero;
import com.minkov.superheroes.models.SuperheroDetails;
import com.minkov.superheroes.tasks.DownloadImgAsyncTask;
import com.minkov.superheroes.tasks.HttpAsyncTask;
import com.minkov.superheroes.tasks.HttpGetAsyncTask;
import com.minkov.superheroes.ui.LoadingFragment;

public class SuperheroDetailsFragment extends Fragment {
    private Superhero superhero;

    public SuperheroDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_superheroes_details, container, false);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        String url = ((SuperheroesApplication) this.getActivity().getApplication())
                .getApiBaseUrl() + "superheroes/" + this.superhero.getId();

        final View root = this.getView();

        final LoadingFragment loading = LoadingFragment.create(this.getActivity());
        loading.show();

        new HttpGetAsyncTask<>(SuperheroDetails.class, new HttpAsyncTask.OnDataReady<SuperheroDetails>() {
            @Override
            public void onReady(SuperheroDetails data) {

                ((TextView) root.findViewById(R.id.tvName))
                        .setText(data.getName());

                ((TextView) root.findViewById(R.id.tvSecretIdentity))
                        .setText(data.getSecretIdentity());

                ((TextView) root.findViewById(R.id.tvStory))
                        .setText(data.getStory());

                final ImageView ivImage = (ImageView) root.findViewById(R.id.ivImage);

                new DownloadImgAsyncTask(new DownloadImgAsyncTask.DownloadImageTaskCallback() {
                    @Override
                    public void call(Bitmap bitmap) {
                        ivImage.setImageBitmap(bitmap);
                        loading.hide();
                    }
                }).execute(data.getImgUrl());
            }
        }).execute(url);
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
