package com.theusmadev.howmuchmycar.utils

import com.theusmadev.howmuchmycar.di.module.AppModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitUtils {
    fun generateRetrofitBuilder(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(AppModule.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun generateOkHttpClientBuilder(
        logging: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(Constants.DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(Constants.DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(Constants.DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .build()
    }
}