package com.project.mandi.domain.repositoryImpl
import com.project.mandi.data.model.SellProduce
import com.project.mandi.db.MandiDAO
import com.project.mandi.domain.MandiRepository
import kotlinx.coroutines.flow.Flow

class MandiRepositoryImpl(private val mandiDAO:MandiDAO): MandiRepository {

    override suspend fun saveSoldProduce(sellProduce:SellProduce) {
        mandiDAO.saveData(sellProduce)
    }

    override suspend fun getSavedData():List<SellProduce> {
        return mandiDAO.getAllProduce()
    }
}