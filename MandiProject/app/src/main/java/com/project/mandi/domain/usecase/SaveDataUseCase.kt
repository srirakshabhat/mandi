package com.project.mandi.domain.usecase

import com.project.mandi.data.model.SellProduce
import com.project.mandi.domain.MandiRepository


class SaveDataUseCase(private val mandiRepository:MandiRepository) {
  suspend fun execute(sellProduce:SellProduce)=mandiRepository.saveSoldProduce(sellProduce)
}