package com.example.assignment.di.base.modules.androidcomponent

import com.example.assignment.ui.detail.DetailActivity
import com.example.assignment.ui.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun detailActivity(): DetailActivity

}