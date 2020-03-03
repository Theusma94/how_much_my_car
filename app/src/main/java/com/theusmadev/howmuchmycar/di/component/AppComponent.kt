package com.theusmadev.howmuchmycar.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import com.theusmadev.howmuchmycar.HowMuchApp
import com.theusmadev.howmuchmycar.di.builder.ActivityBuilder
import com.theusmadev.howmuchmycar.di.module.AppModule
import javax.inject.Singleton


@Singleton
@Component(modules = [(AndroidInjectionModule::class),(AppModule::class),(ActivityBuilder::class)])
interface AppComponent {
    fun inject(application: HowMuchApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appModule(appModule: Application): Builder

        fun build(): AppComponent
    }
}