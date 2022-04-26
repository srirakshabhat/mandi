package com.project.mandi.db
import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.mandi.data.model.SellProduce

@Database(
    entities = [SellProduce::class],
    version = 1,
    exportSchema = false
)
abstract  class MandiDatabase : RoomDatabase(){
    abstract fun getMandiDAO():MandiDAO
}

