package com.example.assignment.di.base.modules.androidcomponent

import com.example.assignment.di.base.modules.androidcomponent.ActivityModule
import dagger.Module

@Module(
    includes = [
        ActivityModule::class,
    ]
)

abstract class AndroidComponentsModule 