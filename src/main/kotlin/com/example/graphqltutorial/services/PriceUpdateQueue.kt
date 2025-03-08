package com.example.graphqltutorial.services

import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue


@Component
class PriceUpdateQueue {

    private val queue = ConcurrentLinkedQueue<String>()
    private val set = ConcurrentHashMap.newKeySet<String>() // Thread-safe set

    fun offer(item: String): Boolean {
        return if (set.add(item)) { // Add to set (returns false if already present)
            queue.offer(item)
            true
        } else {
            false // Item already exists
        }
    }

    fun poll(): String? {
        val item = queue.poll()
        if (item != null) {
            set.remove(item) // Remove from set when dequeued
        }
        return item
    }

    fun peek(): String? = queue.peek()

    fun size(): Int = queue.size

    fun isEmpty(): Boolean = queue.isEmpty()
}