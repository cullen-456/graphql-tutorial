Reference: https://spring.io/guides/gs/graphql-server

Local testing: http://localhost:8080/graphiql?path=/graphql


Query:
```graphql
query bookDetails {
  bookById(id: "book-1") {
    id
    name
    pageCount
    author {
      id
      firstName
      lastName
    }
  }
}
```

Subscription:
```graphql
subscription bookDetails {
  bookAdded {
    id
    name
    pageCount
    author {
      id
      firstName
      lastName
    }
  }
}
```