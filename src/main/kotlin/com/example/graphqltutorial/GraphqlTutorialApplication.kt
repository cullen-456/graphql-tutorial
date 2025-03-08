package com.example.graphqltutorial

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller
import java.util.*

@SpringBootApplication
class GraphqlTutorialApplication

fun main(args: Array<String>) {
    runApplication<GraphqlTutorialApplication>(*args)
}

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
}

@JvmRecord
data class Author(val id: String, val firstName: String, val lastName: String) {
    companion object {
        private val authors: List<Author?> = Arrays.asList(
            Author("author-1", "Joshua", "Bloch"),
            Author("author-2", "Douglas", "Adams"),
            Author("author-3", "Bill", "Bryson")
        )

        fun getById(id: String): Author? {
            return authors.stream()
                .filter { author: Author? -> author!!.id == id }
                .findFirst()
                .orElse(null)
        }
    }
}

@JvmRecord
data class Book(val id: String, val name: String, val pageCount: Int, val authorId: String) {
    companion object {
        private val books: List<Book?> = Arrays.asList(
            Book("book-1", "Effective Java", 416, "author-1"),
            Book("book-2", "Hitchhiker's Guide to the Galaxy", 208, "author-2"),
            Book("book-3", "Down Under", 436, "author-3")
        )

        fun getById(id: String): Book? {
            return books.stream()
                .filter { book: Book? -> book!!.id == id }
                .findFirst()
                .orElse(null)
        }
    }
}