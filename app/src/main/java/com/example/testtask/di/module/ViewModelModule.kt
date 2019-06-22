package com.example.testtask.di.module

import dagger.Module
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.di.ViewModelFactory
import com.example.testtask.di.ViewModelKey
import com.example.testtask.transport.SharedViewModel
import dagger.multibindings.IntoMap
import dagger.Binds

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SharedViewModel::class)
    internal abstract fun postSharedViewModell(viewModel: SharedViewModel): ViewModel
}