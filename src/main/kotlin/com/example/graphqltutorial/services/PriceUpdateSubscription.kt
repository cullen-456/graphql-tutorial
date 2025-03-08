package com.example.graphqltutorial.services

import com.example.graphqltutorial.model.PriceUpdate
import com.example.graphqltutorial.services.PriceUpdateQueue
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.springframework.stereotype.Component

@Component
class PriceUpdateSubscription(
    private val queue: PriceUpdateQueue,
    private val cache: PriceUpdateCache,
) {
    fun priceUpdates(): Flow<PriceUpdate> = flow {
        while (true) {
            val nextIsin = queue.poll()
            if (nextIsin != null) {
                val nextPrice = cache.getById(nextIsin)
                if (nextPrice != null) {
                    emit(nextPrice)
                }
            }
            delay(1000)
        }
    }
}