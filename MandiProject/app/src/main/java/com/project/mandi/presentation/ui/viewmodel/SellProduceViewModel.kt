package com.project.mandi.presentation.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.project.mandi.data.model.SellProduce
import com.project.mandi.domain.usecase.GetSavedDataUseCase
import com.project.mandi.domain.usecase.SaveDataUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SellProduceViewModel(
        private val app:Application,
        private val saveDataUseCase:SaveDataUseCase,
        private val getSavedDataUseCase:GetSavedDataUseCase,
                          ) : AndroidViewModel(app) {

    internal val getMutableLiveData = MutableLiveData<List<SellProduce>>()

    //save seller to room database
    fun saveSeller(sellProduce:SellProduce) = viewModelScope.launch {
        saveDataUseCase.execute(sellProduce)
    }

    fun getSavedData() = viewModelScope.launch {
        val result = getSavedDataUseCase.execute()
        getMutableLiveData.postValue(result)
    }

}














