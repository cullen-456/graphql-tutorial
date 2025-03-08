package com.example.graphqltutorial.services

import com.example.graphqltutorial.model.PriceUpdate
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class PriceUpdateCache {
    private val priceUpdates: MutableMap<String, PriceUpdate> = ConcurrentHashMap()

    fun addOrReplace(priceUpdate: PriceUpdate) {
        priceUpdates[priceUpdate.isin] = priceUpdate
    }

    fun getById(id: String): PriceUpdate? {
        return priceUpdates[id]
    }

    fun getAll(): List<PriceUpdate> {
        return priceUpdates.values.toList()
    }
}