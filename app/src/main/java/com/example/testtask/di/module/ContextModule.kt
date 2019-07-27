package com.example.testtask.di.module

import android.app.Application
import android.content.Context
import com.example.sdk.core.network.NetworkHelper
import com.example.sdk.core.network.NetworkHelperImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(private val context: Application) {

    @Provides
    fun provideContext():Context = context

    @Provides
    @Singleton
    fun provideInternetChecker(context: Context):NetworkHelper = NetworkHelperImpl(context)
}