package com.example.graphqltutorial.controller

import com.example.graphqltutorial.model.Author
import com.example.graphqltutorial.model.Book
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.graphql.data.method.annotation.SubscriptionMapping
import org.springframework.stereotype.Controller
import kotlin.random.Random

@Controller
class BookController {
    @QueryMapping
    fun bookById(@Argument id: String?): Book {
        return Book.getById(id!!)!!
    }

    @SchemaMapping
    fun author(book: Book): Author {
        return Author.getById(book.authorId)!!
    }

    @SubscriptionMapping
    fun bookAdded(): Flow<Book> = flow {
        while(true){
            val id = "book-" + (4 + Random.nextInt(1000))
            val name = "Random Book " + Random.nextInt(100)
            val pageCount = 100 + Random.nextInt(900)
            val authorId = "author-" + (1 + Random.nextInt(3))
            val book = Book(id, name, pageCount, authorId)
            emit(book)
            delay(1000)
        }
    }
}