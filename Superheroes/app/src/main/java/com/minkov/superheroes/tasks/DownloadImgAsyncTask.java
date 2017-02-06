package com.minkov.superheroes.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadImgAsyncTask extends AsyncTask<String, String, Bitmap> {

    private final DownloadImageTaskCallback downloadImageTaskCallback;

    public DownloadImgAsyncTask(DownloadImageTaskCallback downloadImageTaskCallback){
        this.downloadImageTaskCallback = downloadImageTaskCallback;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String url = params[0];

        OkHttpClient client = new OkHttpClient();
        Request req = new Request.Builder()
                .url(url)
                .build();

        Response res = null;
        try {
            res = client.newCall(req).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStream inputStream = res.body().byteStream();
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        this.downloadImageTaskCallback.call(bitmap);
    }

    public interface DownloadImageTaskCallback{
        void call(Bitmap bitmap);
    }


}
