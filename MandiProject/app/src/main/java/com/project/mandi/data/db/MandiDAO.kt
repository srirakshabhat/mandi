package com.project.mandi.db

import androidx.room.*
import com.project.mandi.data.model.SellProduce
import kotlinx.coroutines.flow.Flow

@Dao
interface MandiDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveData(sellProduce:SellProduce)

    @Query("SELECT * FROM sellproduce")
    suspend fun getAllProduce(): List<SellProduce>

}