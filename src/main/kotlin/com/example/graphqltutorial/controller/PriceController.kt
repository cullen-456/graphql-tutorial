package com.example.graphqltutorial.controller

import com.example.graphqltutorial.model.PriceUpdate
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SubscriptionMapping
import org.springframework.stereotype.Controller
import java.util.*
import kotlin.random.Random

@Controller
class PriceController {

    @QueryMapping
    fun priceByIsin(@Argument isin: String?): PriceUpdate {
        return PriceUpdate.getByIsin(isin!!)!!
    }

    @SubscriptionMapping
    fun priceUpdate(): Flow<PriceUpdate> = flow {
        while (true) {
            val id = "price-" + (4 + Random.nextInt(1000))
            val bid = 100.0 + Random.nextDouble(900.0)
            val ask = bid + Random.nextDouble(100.0)
            val isin = "isin-" + (1 + Random.nextInt(3))
            val timestamp = Date().toString()
            val priceUpdate = PriceUpdate(id, bid, ask, isin, timestamp)
            emit(priceUpdate)
            delay(1000)
        }
    }
}