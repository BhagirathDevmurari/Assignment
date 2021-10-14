package com.example.assignment.di.base.modules

import com.example.assignment.data.remote.api.DashApi
import com.example.assignment.di.base.ApiEndpoint
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object ApiModule {
    @Singleton
    @Provides
    fun dashApi(@ApiEndpoint retrofit: Retrofit): DashApi {
        return retrofit.create(DashApi::class.java)
    }
}