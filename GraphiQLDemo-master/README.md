# GraphiQLDemo
GraphQL Demo with GraphiQL

The project is a demo to understand the concepts and workflow of GraphQL using GraphiQL interface.

The steps to run and test the application:
1. Run the application as a Spring Maven Application
2. You can use postman to run the endpoints
3. You can check the embedded database h2-console at "http://localhost:8080/h2-console"
4. You can use the graphiQL interface to execute GraphQL queries at "http://localhost:8080/graphiql"

Sample GraphiQL Queries/Mutations:
1. Mutation
```graphql
mutation {
  newAuthor(firstName:"ABC",lastName:"DEF") 
  {
    id
  },
  newBook (title:"qwerty", isbn:"13", pageCount:12, author: 3) 
  {
      id
  }
}
```
2. Query
```graphql
query {
  findAllAuthors 
  {
    id,
    firstName,
    lastName
    }
}
```
3. Query
```graphql
query {
  findAllBooks
  {
    id,
    title,
    isbn,
    pageCount,
    author {
      id,
      firstName,
      lastName
    }
    }
}
```
4. Query
```graphql
query {
  countBooks
}
```
