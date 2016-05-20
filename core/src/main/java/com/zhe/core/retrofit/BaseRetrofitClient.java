package com.zhe.core.retrofit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhe on 16/3/7.
 */
public class BaseRetrofitClient {

    protected static OkHttpClient okHttpClient;

    private <T> T initRestAdapters(String ENDPOINT, Class<T> restInterface) {
        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain
                        .request()
                        .newBuilder()
                        .addHeader("User-Agent", "Retrofit-Sample-App")
                        .build();
                return chain.proceed(newRequest);
            }
        };

        // Add the interceptor to OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        okHttpClient = builder.build();


        // Set the custom client when building adapter
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(restInterface);
    }

    public <T> T initRestAdapter(String ENDPOINT, Class<T> restInterface) {
        return initRestAdapters(ENDPOINT, restInterface);
    }
}
