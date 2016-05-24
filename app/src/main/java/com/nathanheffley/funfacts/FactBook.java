package com.nathanheffley.funfacts;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class FactBook extends AsyncTask<URL, Integer, String> {
    private static final String TAG = FactBook.class.getSimpleName();
    private static final String API_URL = "http://theresonance.space/api/0-0-1/";

    private static String currentFact = "I couldn't find a fun fact for you :(";

    public static String getFact() {
        URL url;
        try {
            url = new URL(API_URL + "funfact");
            currentFact = new FactBook().execute(url).get();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return currentFact;
    }

    @Override
    protected String doInBackground(URL... urls) {
        String fact = "I couldnt' find a fun fact for you :(";
        try {
            URL url = urls[0];
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\A");
                fact = s.hasNext() ? s.next() : "";
            } finally {
                urlConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fact;
    }
}
