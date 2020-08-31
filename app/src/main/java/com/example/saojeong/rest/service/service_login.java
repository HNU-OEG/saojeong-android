package com.example.saojeong.rest.service;

import android.text.TextUtils;

import com.example.saojeong.rest.AuthenticationInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class service_login {
    public static final String API_BASE_URL = "https://saojeong-dev.hnulinc.c11.kr/";

        private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        private static Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        private static Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson));

        private static Retrofit retrofit = builder.build();

        public static <S> S createService(Class<S> serviceClass) {
            return createService(serviceClass, null);
        }

        public static <S> S createService(
                Class<S> serviceClass, final String authToken) {
            if (!TextUtils.isEmpty(authToken)) {
                AuthenticationInterceptor interceptor =
                        new AuthenticationInterceptor("Bearer " + authToken);

                if (!httpClient.interceptors().contains(interceptor)) {
                    httpClient.addInterceptor(interceptor);

                    builder.client(httpClient.build());
                    retrofit = builder.build();
                }
            }

            return retrofit.create(serviceClass);
        }
}
