package com.stackroute.graphqldemo.repository;
import org.springframework.data.repository.CrudRepository;

import com.stackroute.graphqldemo.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
