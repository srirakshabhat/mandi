package com.project.mandi.presentation.di
import android.app.Application
import androidx.room.Room
import com.project.mandi.db.MandiDAO
import com.project.mandi.db.MandiDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Singleton
    @Provides
    fun provideMandiDatabase(app: Application):MandiDatabase {
        return Room.databaseBuilder(app, MandiDatabase::class.java, "mandi_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMandiDao(mandiDatabase: MandiDatabase):MandiDAO {
        return mandiDatabase.getMandiDAO()
    }


}