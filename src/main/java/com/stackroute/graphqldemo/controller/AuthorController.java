package com.stackroute.graphqldemo.controller;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.stackroute.graphqldemo.model.Author;
import com.stackroute.graphqldemo.repository.AuthorRepository;

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

