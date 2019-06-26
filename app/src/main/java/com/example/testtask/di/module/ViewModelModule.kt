package com.example.testtask.di.module

import dagger.Module
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.di.ViewModelFactory
import com.example.testtask.di.ViewModelKey
import com.example.testtask.viewmodel.MainActivityViewModel
import com.example.testtask.viewmodel.transport.SharedViewModel
import dagger.multibindings.IntoMap
import dagger.Binds

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SharedViewModel::class)
    internal abstract fun postSharedViewModel(viewModel: SharedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    internal abstract fun provideMainActivityViewModel(viewModel: MainActivityViewModel): ViewModel}