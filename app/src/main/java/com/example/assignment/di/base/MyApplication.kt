package com.example.assignment.di.base

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.assignment.data.repo.dash.DashRepo
import com.example.assignment.di.base.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class MyApplication : DaggerApplication() {
    private var context: Context? = null
    private lateinit var dashRepo: DashRepo

    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    fun setCurrentActivity(context: Context) {
        this.context = context
    }
    fun setAuthRepo(dashRepo: DashRepo) {
        this.dashRepo = dashRepo
    }


    companion object {
        private val TAG = MyApplication::class.java.simpleName
        @SuppressLint("StaticFieldLeak")
        @JvmStatic
        var instance: MyApplication? = null
            private set
    }
}