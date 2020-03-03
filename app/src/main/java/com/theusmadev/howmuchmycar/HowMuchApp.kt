package com.theusmadev.howmuchmycar

import android.app.Activity
import android.app.Application
import com.theusmadev.howmuchmycar.di.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector


import javax.inject.Inject


class HowMuchApp : Application(), HasActivityInjector {


    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = activityDispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .appModule(this)
            .build()
            .inject(this)
    }

}


