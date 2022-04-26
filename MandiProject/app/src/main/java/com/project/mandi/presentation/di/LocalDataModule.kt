package com.project.mandi.presentation.di

import com.project.mandi.domain.repositoryImpl.MandiRepositoryImpl
import com.project.mandi.db.MandiDAO
import com.project.mandi.domain.MandiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideLocalDataSource(mandiDAO:MandiDAO):MandiRepository {
      return MandiRepositoryImpl(mandiDAO)
    }

}













