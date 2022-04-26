package com.project.mandi.presentation.di

import android.app.Application
import com.project.mandi.domain.usecase.GetSavedDataUseCase
import com.project.mandi.domain.usecase.SaveDataUseCase
import com.project.mandi.presentation.ui.SellProduceViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
  fun provideMandiViewModelFactory(
            application: Application,
            saveDataUseCase:SaveDataUseCase,
            getSavedDataUseCase:GetSavedDataUseCase,
  ):SellProduceViewModelFactory {
      return SellProduceViewModelFactory(
          application,
          saveDataUseCase,
          getSavedDataUseCase
      )
  }

}








