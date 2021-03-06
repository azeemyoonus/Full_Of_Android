package com.example.android.datafrominternet.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtlis {

    final static String GITHUB_BASE_URL = "https://api.github.com/search/repositories";
    final static String PARAM_QUERY="q";
    /*final static String PARAM_SORT="stars"; */

    public static URL buildUrl(String githubquery){
        Uri buildUri =Uri.parse(GITHUB_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY,githubquery)
                /*.appendQueryParameter(PARAM_SORT, sortBy)*/
                .build();
                URL url =null;
                try {
                    url = new URL(buildUri.toString());
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }
                return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException{
        HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
        try{
            InputStream in =urlConnection.getInputStream();
            Scanner scanner =new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
                    if(hasInput){
                        return scanner.next();
                    }else {
                        return null;
                    }

        }finally {
            urlConnection.disconnect();
        }
    }
}

