package com.fabindia.shopping.network;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RestClient {
    private static final String BASE_URL = "https://restcountries.eu/rest/v2/";
    private static ApiServices mApiServices;
    private static RestClient mInstance;

    public static RestClient getInstance(){

        if(mInstance == null){
            mInstance = new RestClient();
        }
        return mInstance;
    }

    private RestClient() {
        OkHttpClient okHttpClient = (new OkHttpClient()).
                newBuilder().
                connectTimeout(15L, TimeUnit.SECONDS).
                readTimeout(15L, TimeUnit.SECONDS).
                build();
        Retrofit retrofit = (new Retrofit.Builder()).
                baseUrl(BASE_URL).
                client(okHttpClient).
                addConverterFactory((Converter.Factory) GsonConverterFactory.create()).build();
        ApiServices apiServices = retrofit.create(ApiServices.class);
        mApiServices = (ApiServices)apiServices;
    }


 public ApiServices getApiService(){
        return mApiServices;
 }



}
