package com.project.mandi.presentation.di

import com.project.mandi.domain.MandiRepository
import com.project.mandi.domain.usecase.GetSavedDataUseCase
import com.project.mandi.domain.usecase.SaveDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
   @Singleton
   @Provides
   fun provideSaveDataUseCase(
     mandiRepository:MandiRepository
   ):SaveDataUseCase{
      return SaveDataUseCase(mandiRepository)
   }

   @Singleton
   @Provides
   fun provideGetSavedDataUseCase(mandiRepository:MandiRepository
   ):GetSavedDataUseCase {
      return GetSavedDataUseCase(mandiRepository)
   }

}


















