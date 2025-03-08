package com.example.graphqltutorial.services

import com.example.graphqltutorial.model.PriceUpdate
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.springframework.stereotype.Component
import java.util.*
import kotlin.random.Random


@Component
class PriceUpdateSource {

    fun priceUpdates(): Flow<PriceUpdate> = flow {
        while (true) {
            val id = "price-" + (4 + Random.nextInt(1000))
            val bid = 100.0 + Random.nextDouble(900.0)
            val ask = bid + Random.nextDouble(100.0)
            val isin = "isin-" + (1 + Random.nextInt(3))
            val timestamp = Date().toString()
            val priceUpdate = PriceUpdate(id, bid, ask, isin, timestamp)
            emit(priceUpdate)
            delay(10)
        }
    }

}


