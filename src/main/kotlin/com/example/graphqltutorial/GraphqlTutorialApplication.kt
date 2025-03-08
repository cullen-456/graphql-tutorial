package com.example.graphqltutorial

import com.example.graphqltutorial.services.PriceUpdateCache
import com.example.graphqltutorial.services.PriceUpdateQueue
import com.example.graphqltutorial.services.PriceUpdateSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.subscribe
import kotlinx.coroutines.launch
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GraphqlTutorialApplication

fun main(args: Array<String>) {
    val context = runApplication<GraphqlTutorialApplication>(*args)


    val priceSource = context.getBean(PriceUpdateSource::class.java)
    val priceCache = context.getBean(PriceUpdateCache::class.java)
    val priceQueue = context.getBean(PriceUpdateQueue::class.java)

    CoroutineScope(Dispatchers.Default).launch {
        priceSource.priceUpdates()
            .onEach { priceUpdate ->
                priceCache.addOrReplace(priceUpdate)
                priceQueue.offer(priceUpdate.isin)
            }
            .launchIn(this) // This starts the flow collection
    }



}

