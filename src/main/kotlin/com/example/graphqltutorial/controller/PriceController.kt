package com.example.graphqltutorial.controller

import com.example.graphqltutorial.model.PriceUpdate
import com.example.graphqltutorial.services.PriceUpdateSubscription
import kotlinx.coroutines.flow.Flow
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SubscriptionMapping
import org.springframework.stereotype.Controller

@Controller
class PriceController(
    private val priceUpdateSource: PriceUpdateSubscription
) {

    @QueryMapping
    fun priceByIsin(@Argument isin: String?): PriceUpdate {
        return PriceUpdate.getByIsin(isin!!)!!
    }

    @SubscriptionMapping
    fun priceUpdate(): Flow<PriceUpdate> = priceUpdateSource.priceUpdates()
}