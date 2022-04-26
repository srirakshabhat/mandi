package com.project.mandi.presentation.ui
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.mandi.domain.usecase.GetSavedDataUseCase
import com.project.mandi.domain.usecase.SaveDataUseCase
import com.project.mandi.presentation.ui.viewmodel.SellProduceViewModel

class SellProduceViewModelFactory(
        private val app:Application,
        private val saveDataUseCase:SaveDataUseCase,
        private val getSavedDataUseCase:GetSavedDataUseCase,

        ):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SellProduceViewModel(
            app,
            saveDataUseCase,
            getSavedDataUseCase) as T
    }
}









