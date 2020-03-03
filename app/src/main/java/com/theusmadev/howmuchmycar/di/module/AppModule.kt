package com.theusmadev.howmuchmycar.di.module

import android.app.Application
import android.content.Context
import com.theusmadev.howmuchmycar.di.factory.ViewModelFactoryModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelFactoryModule::class])
class AppModule {

    @Provides
    @Singleton
    fun providesContext(application: Application): Context = application


}