package com.project.mandi.data.model

data class VillageData(private val village_name: String,  val price: Double){
    override fun toString():String {
        return village_name
    }
}
