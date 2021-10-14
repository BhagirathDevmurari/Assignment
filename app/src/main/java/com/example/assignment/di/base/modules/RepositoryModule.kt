package com.example.assignment.di.base.modules

import com.example.assignment.data.remote.api.DashApi
import com.example.assignment.data.repo.dash.DashRepo
import com.example.assignment.data.repo.dash.DashRepoImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun dashRepo(dashApi: DashApi): DashRepo {
        return DashRepoImpl(dashApi)
    }
}