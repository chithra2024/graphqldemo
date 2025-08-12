package com.stackroute.graphqldemo;

import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.stackroute.graphqldemo.model.Author;
import com.stackroute.graphqldemo.model.Book;
import com.stackroute.graphqldemo.repository.AuthorRepository;
import com.stackroute.graphqldemo.repository.BookRepository;

@SpringBootApplication
public class GraphqldemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqldemoApplication.class, args);
	}

	 @Bean
    ApplicationRunner applicationRunner(AuthorRepository authorRepository, BookRepository bookRepository) {
        return args -> {
            Author maciej = authorRepository.save(new Author("Maciej"));
            Author josh = authorRepository.save(new Author("Josh"));

            bookRepository.saveAll(List.of(
                    new Book("Book 1", "Publisher A", maciej),
                    new Book("Book 2", "Publisher B", maciej),
                    new Book("Book 3", "Publisher C", josh)
            ));
        };
    }

}


// Go to GraphiQL:

// http://localhost:8086/graphiql

// Paste and run this query:


// query {
//   authors {
//     id
//     name
//     books {
//       title
//       publisher
//     }
//   }
// }