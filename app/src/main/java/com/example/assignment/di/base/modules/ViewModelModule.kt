package com.example.assignment.di.base.modules

import androidx.lifecycle.ViewModelProvider
import com.example.assignment.di.base.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        ActivityViewModelModule::class
    ]
)

abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}