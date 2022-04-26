package com.project.mandi.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
        tableName = "sellproduce"
       )
data class SellProduce(
        @PrimaryKey(autoGenerate = false)
        @SerializedName("sellerName")
        val sellerName: String,
        @SerializedName("loyalty_card_id")
        val loyalty_card_id:String)
