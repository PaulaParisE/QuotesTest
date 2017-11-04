package com.example.paulapariselias.quotestest.network;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by paulapariselias on 30-09-17.
 */

public class Interceptor {

    private static final String BASE_URL = "https://andruxnet-random-famous-quotes.p.mashape.com";


    public Quotes aCommonGetInterceptor() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS);

        httpClient.addInterceptor(new okhttp3.Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request request = originalRequest.newBuilder()
                    /*Common headers*/
                        .header("X-Mashape-Key", "rOFZlQ7rpamshWyBgOLeNc1rgkAXp1npSJAjsnpJfPpNgTlGBs")
                        .header("Accept", "application/json")
                    /*Custom header*/
                        //.header("Flavor", "mint")
                        .build();

                Response response = chain.proceed(request);

            /*If the request fail then you get 3 retrys*/
                int retryCount = 0;
                while (!response.isSuccessful() && retryCount < 3) {
                    retryCount++;
                    response = chain.proceed(request);
                }

                return response;
            }
        });

        OkHttpClient client = httpClient.build();

        Retrofit interceptor = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        Quotes request = interceptor.create(Quotes.class);

        return request;
    }


}
