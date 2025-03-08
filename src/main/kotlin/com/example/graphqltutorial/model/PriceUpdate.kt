package com.example.graphqltutorial.model

import java.util.*


data class PriceUpdate(
    val id: String,
    val bid:Double,
    val ask:Double,
    val isin:String,
    val timestamp: String
){
    companion object {
        private val priceUpdates: List<PriceUpdate?> = listOf(
            PriceUpdate("priceUpdate-1", 100.0, 101.0, "isin-1", Date().toString()),
            PriceUpdate("priceUpdate-2", 200.0, 201.0, "isin-2", Date().toString()),
            PriceUpdate("priceUpdate-3", 300.0, 301.0, "isin-3", Date().toString()),
        )

        fun getByIsin(isin: String): PriceUpdate? {
            return priceUpdates.stream()
                .filter { priceUpdate: PriceUpdate? -> priceUpdate!!.isin == isin }
                .findFirst()
                .orElse(null)
        }
    }
}