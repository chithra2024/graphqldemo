package com.stackroute.graphqldemo.repository;
import org.springframework.data.repository.CrudRepository;

import com.stackroute.graphqldemo.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
}
