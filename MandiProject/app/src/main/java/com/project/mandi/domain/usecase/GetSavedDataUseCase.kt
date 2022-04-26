package com.project.mandi.domain.usecase

import com.project.mandi.data.model.SellProduce
import com.project.mandi.domain.MandiRepository
import kotlinx.coroutines.flow.Flow

class GetSavedDataUseCase(private val mandiRepository:MandiRepository) {
    suspend fun execute():List<SellProduce> {
        return mandiRepository.getSavedData()
    }
}