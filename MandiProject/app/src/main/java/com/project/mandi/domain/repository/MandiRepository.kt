package com.project.mandi.domain
import com.project.mandi.data.model.SellProduce
import kotlinx.coroutines.flow.Flow

interface MandiRepository{
      suspend fun saveSoldProduce(sellProduce:SellProduce)
      suspend fun getSavedData():List<SellProduce>
}