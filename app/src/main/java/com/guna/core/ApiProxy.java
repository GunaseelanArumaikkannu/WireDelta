package com.guna.core;

/**
 * Created by Gunaseelan on 10-12-2015.
 * ApiProxy is used to do network operations.
 */
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiProxy {

    /**
     * get is used to do GET method operations in network.
     */
    public static Response get(String URl) {
        Log.v("Posmate", "stringUrl : " + URl);
        Response result = new Response();
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(URl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("content-type", "application/json");
            urlConnection.setRequestMethod("GET");

            result.setStatus(false);

            final int statusCode = urlConnection.getResponseCode();
            InputStream inputStream;
            if (statusCode == HttpURLConnection.HTTP_OK) {
                result.setStatus(true);
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
                result.setResult(convertInputStreamToString(inputStream));
            } else if (statusCode == HttpURLConnection.HTTP_INTERNAL_ERROR) {
                result.setMessage("An error occurred while processing your request.");
            } else {
                inputStream = new BufferedInputStream(urlConnection.getErrorStream());
                result.setMessage(convertInputStreamToString(inputStream));
            }
        } catch (Exception ex) {
            assert urlConnection != null;
            urlConnection.disconnect();
            ex.printStackTrace();
            result.setMessage("No Connection");
        }
        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;

        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}

