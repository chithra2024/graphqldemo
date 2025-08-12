GraphQL schema or API schema
============================
add dependency: 

  <!-- Spring GraphQL -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-graphql</artifactId>
    </dependency>


install plugin graphql in intellij
resources/graphql/schema.graphqls

example1:

type Query {
    authors: [Author]
}

type Author {
    id: ID!
    name: String!
    books: [Book]
}

type Book {
    id: ID!
    title: String!
    publisher: String
}


package com.example.demo.controller;

import com.example.demo.entity.Author;
import com.example.demo.repository.AuthorRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // Method name matches schema: "authors"
    @QueryMapping
    public Iterable<Author> authors() {
        return authorRepository.findAll();
    }
}

application.properties


spring.graphql.graphiql.enabled = true


localhost:8086/graphiql

query {
  authors {
    id
    name
    books {
      title
      publisher
    }
  }
}

Example2:

type Query{
   authors: [Author]
   authorById(id: ID!): Author

}

type Author {

  id: ID!
  name: String!
  books: [Book]

}

type Book {
   id: ID!
   title: String!
   publisher: String

}

@QueryMapping
Optional<Author> authorById(@Argument Long id){
  return authorRepository.findById(id);

}



query {

  quthorById(id: 1) {

   id
   name
 }


example3:

we can execute both query at a time


query {

  quthorById(id: 1) {

   id
   name
 }
   authors {
    id
    name
    books {
      title
      publisher
    }
  }

}
//single http request gives http response
return all the queries or required information

example 4: Mutation


type Query{
   authors: [Author]
   authorById(id: ID!): Author

}

type Mutation {
  addBook(book: BookInput) : Book

}

input BookInput{
   title: String!
   publisher: String
   authorId: ID!

}

type Author {

  id: ID!
  name: String!
  books: [Book]

}

type Book {
   id: ID!
   title: String!
   publisher: String

}

@MutationMapping
private BookRepository bookRepository;

intialize in constructor also

Book addBook(@Argument BookInput book){
Author author=authorRepository.findById(book.authorId()).orElseThrow(()-> new IllegalArgumentException("author not found"))
Book b=new Book(book.title(), book.publisher, author);
return bookRepository.save(b);

}

// input parameters are DTOs

record BookInput(String title, String publisher, Long authorId){}

mutation{

  addBook(book: {title:"Spring cloud in Action",
publisher:"Manning", authorId:2})
{
 id
}

}

//verification
query{

  authors {

   id
   name
   books {

    id
    title
    publisher
}
}
}


logging.level.sql=debug


query {

  authors {
    id
    name
}}
//verify console

query{

  authors {

   id
   name
   books {

    id
    title
    publisher
}
}
}

// verify console

for each author we select all books thru multiple sql statement. this is called N+1 problem.

spring.jpa.open-in-view=false

through failed to lazily initialize a collection



