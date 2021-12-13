package com.example.navcomponent.netwrok

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    private const val baseUrl = "https://api.spoonacular.com/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(OkHttpClient.Builder().addInterceptor(queryInterceptor).build())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    private val queryInterceptor: Interceptor
        get() = Interceptor { chain ->

            val original: Request = chain.request()

            val originalHttpUrl: HttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("apiKey", "0f3c5f4717ba4db7aaffb5495b3a6474")
                .build()

            val requestBuilder = original.newBuilder().url(url)

            val request = requestBuilder.build()
            chain.proceed(request)
        }

}