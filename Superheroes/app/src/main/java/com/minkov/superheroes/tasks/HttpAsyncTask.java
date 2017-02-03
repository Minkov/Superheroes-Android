package com.minkov.superheroes.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by minkov on 2/3/17.
 */

public abstract class HttpAsyncTask<T> extends AsyncTask<String, String, T> {
    private final Class<T> klass;
    private OnDataReady<T> onDataReady;

    public HttpAsyncTask(Class<T> klass, OnDataReady<T> onDataReady) {
        this.onDataReady = onDataReady;
        this.klass = klass;
    }

    protected Class<T> getKlass() {
        return klass;
    }

    public interface OnDataReady<T> {
        void onReady(T data);
    }


    @Override
    protected T doInBackground(String... params) {
        String url = params[0];
        OkHttpClient client = new OkHttpClient();

        Request req = this.buildRequest(url);

        try {
            Response res = client.newCall(req).execute();
            String json = res.body().string();
            Gson gson = new Gson();

            T data= gson.fromJson(json, this.getKlass());
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected abstract Request buildRequest(String url);

    @Override
    protected void onPostExecute(T data) {
        this.onDataReady.onReady(data);
    }
}
