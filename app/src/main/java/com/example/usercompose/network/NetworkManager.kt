package com.example.usercompose.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    fun getRetrofit(): Retrofit {
        val client = OkHttpClient()
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val clientBuilder: OkHttpClient.Builder=
            client.newBuilder().addInterceptor(interceptor)
        return Retrofit.Builder().baseUrl("https://reqres.in")
            .client(clientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}