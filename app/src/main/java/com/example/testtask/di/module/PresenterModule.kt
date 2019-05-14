package com.example.testtask.di.module

import com.example.testtask.App
import com.example.testtask.presenter.MainPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class PresenterModule {
    @Provides
    @Singleton
    fun provideMainViewPresenter(): MainPresenter = MainPresenter(App.getInstance().injector.db)
}