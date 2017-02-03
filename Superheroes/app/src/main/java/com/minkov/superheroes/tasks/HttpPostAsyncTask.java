package com.minkov.superheroes.tasks;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class HttpPostAsyncTask<T> extends HttpAsyncTask<T> {
    private T data;

    public HttpPostAsyncTask(T data, Class<T> klass, HttpAsyncTask.OnDataReady<T> onDataReady) {
        super(klass, onDataReady);
        this.data = data;
    }

    @Override
    protected Request buildRequest(String url) {
        String json = new Gson().toJson(this.data);

        RequestBody body =
                RequestBody.create(MediaType.parse("application/json"), json);

        return new Request.Builder()
                .url(url)
                .post(body)
                .build();
    }
}
