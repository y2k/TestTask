package com.example.testtask.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: Application) {

    @Singleton
    @Provides
    internal fun provideApplication(): Application = app
}