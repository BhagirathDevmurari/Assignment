package com.example.assignment.di.base.modules

import androidx.lifecycle.ViewModel
import com.example.assignment.ui.detail.DetailActivityVM
import com.example.assignment.ui.home.MainActivityVM
import com.iunctainc.iuncta.app.di.mapkey.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ActivityViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityVM::class)
    abstract fun mainActivity(vm: MainActivityVM): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(DetailActivityVM::class)
    abstract fun detailActivityVM(vm: DetailActivityVM): ViewModel

}