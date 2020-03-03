package com.theusmadev.howmuchmycar.di.builder

import com.theusmadev.howmuchmycar.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.theusmadev.howmuchmycar.ui.main.MainModule

@Module
abstract class ActivityBuilder{

    @ContributesAndroidInjector(modules = [(MainModule::class)])
    abstract fun bindMainActivity(): MainActivity

}