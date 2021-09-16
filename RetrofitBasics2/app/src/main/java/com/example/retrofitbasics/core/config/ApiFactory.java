package com.example.retrofitbasics.core.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {

    public static final int TIMEOUT = 30;
    public static final String BASE_URL = "http://192.168.1.4:3000/";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";

    public Retrofit create() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(getInterceptorFor(HttpLoggingInterceptor.Level.HEADERS))
                .addInterceptor(getInterceptorFor(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(getGsonBuilder()))
                .client(client)
                .build();
    }

    @NotNull
    private Gson getGsonBuilder() {
        return new GsonBuilder().setDateFormat(DATE_TIME_PATTERN).create();
    }

    private HttpLoggingInterceptor getInterceptorFor(HttpLoggingInterceptor.Level level){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(level);

        return interceptor;
    }
}
