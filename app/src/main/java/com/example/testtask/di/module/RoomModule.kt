package com.example.testtask.di.module

import dagger.Provides
import javax.inject.Singleton
import android.app.Application
import androidx.room.Room
import com.example.room.EmployeeDatabase
import dagger.Module

@Module
class RoomModule(mApplication: Application) {

    private var db: EmployeeDatabase = Room
        .databaseBuilder(
            mApplication,
            EmployeeDatabase::class.java,
            EmployeeDatabase.DATABASE_NAME.plus(".db")
        )
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()

    @Singleton
    @Provides
    internal fun providesRoomDatabase(): EmployeeDatabase {
        return db
    }
}