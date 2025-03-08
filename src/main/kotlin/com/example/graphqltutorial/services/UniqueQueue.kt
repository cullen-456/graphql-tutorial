package com.example.graphqltutorial.services

import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class UniqueQueue<T> {
    private val queue = ConcurrentLinkedQueue<T>()
    private val set = ConcurrentHashMap.newKeySet<T>() // Thread-safe set

    fun offer(item: T): Boolean {
        return if (set.add(item)) { // Add to set (returns false if already present)
            queue.offer(item)
            true
        } else {
            false // Item already exists
        }
    }

    fun poll(): T? {
        val item = queue.poll()
        if (item != null) {
            set.remove(item) // Remove from set when dequeued
        }
        return item
    }

    fun peek(): T? = queue.peek()

    fun size(): Int = queue.size

    fun isEmpty(): Boolean = queue.isEmpty()
}