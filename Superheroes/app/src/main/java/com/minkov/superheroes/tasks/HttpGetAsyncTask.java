package com.minkov.superheroes.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpGetAsyncTask<T> extends HttpAsyncTask<T> {

    public HttpGetAsyncTask(Class<T> klass, OnDataReady<T> onDataReady) {
        super(klass, onDataReady);
    }

    @Override
    protected Request buildRequest(String url) {
        return new Request.Builder()
                .url(url)
                .build();
    }
}
