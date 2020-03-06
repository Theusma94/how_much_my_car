package com.theusmadev.howmuchmycar.di.module

import android.app.Application
import android.content.Context
import com.theusmadev.howmuchmycar.data.CarInfoService
import com.theusmadev.howmuchmycar.data.remote.CarRepository
import com.theusmadev.howmuchmycar.di.factory.ViewModelFactoryModule
import com.theusmadev.howmuchmycar.utils.RetrofitUtils.generateOkHttpClientBuilder
import com.theusmadev.howmuchmycar.utils.RetrofitUtils.generateRetrofitBuilder
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module(includes = [ViewModelFactoryModule::class])
class AppModule {

    companion object {
        val baseUrl = "https://volanty-price-api.herokuapp.com/"
    }

    @Provides
    @Singleton
    fun providesContext(application: Application): Context = application

    @Provides
    @Singleton
    fun providesApiService(): CarInfoService {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = generateOkHttpClientBuilder(logging)
        val retrofit = generateRetrofitBuilder(okHttpClient)
        return retrofit.create(CarInfoService::class.java)
    }

    @Provides
    @Singleton
    fun providesCarRepository(carInfoService: CarInfoService) = CarRepository(carInfoService)



}