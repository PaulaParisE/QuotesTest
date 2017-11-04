package com.example.paulapariselias.quotestest.network;

import android.os.AsyncTask;

import com.example.paulapariselias.quotestest.models.Quote;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by paulapariselias on 30-09-17.
 */

public class GetQuotes extends AsyncTask<String,Void, Quote> {


    @Override
    //cambie del doInBackground (String ...string)
    protected Quote doInBackground(String... params) {


        Quotes quotes = new Interceptor().aCommonGetInterceptor();
        Call<Quote> randomQuotes = quotes.randomQuotes();
        try {
            Response<Quote> response = randomQuotes.execute();
            if (200 == response.code() && response.isSuccessful()){
                return response.body();
            }else{
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return  null;

        }



    }


}
