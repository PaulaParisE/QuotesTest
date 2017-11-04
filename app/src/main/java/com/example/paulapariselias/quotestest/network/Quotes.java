package com.example.paulapariselias.quotestest.network;

import com.example.paulapariselias.quotestest.models.Quote;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by paulapariselias on 30-09-17.
 */

public interface Quotes {

    @GET(".")
    Call<Quote> randomQuotes();

}
