package com.example.assignment.di.base.component;


import com.example.assignment.di.base.AppModule;
import com.example.assignment.di.base.modules.ApiModule;
import com.example.assignment.di.base.modules.NetworkModule;
import com.example.assignment.di.base.modules.RepositoryModule;
import com.example.assignment.di.base.modules.ViewModelModule;
import com.example.assignment.di.base.modules.androidcomponent.AndroidComponentsModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ApiModule.class,
        RepositoryModule.class,
        NetworkModule.class,
        AndroidComponentsModule.class,
        ViewModelModule.class,


})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<DaggerApplication> {

    }


}
