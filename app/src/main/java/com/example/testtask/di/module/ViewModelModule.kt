package com.example.testtask.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.ReduxViewModel
import com.example.testtask.di.ViewModelFactory
import com.example.testtask.di.ViewModelKey
import com.example.testtask.view.viewmodel.Model
import com.example.testtask.view.viewmodel.SharedViewModelKey
import com.example.testtask.view.viewmodel.SharedViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Module
    companion object {
        @JvmStatic
        @IntoMap
        @Provides
        @ViewModelKey(SharedViewModelKey::class)
        internal fun postSharedViewModel(): ViewModel =
            ReduxViewModel(Model(), SharedViewModel::sub, SharedViewModel::update)
    }
}