package com.openlibrary.all.books.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openlibrary.all.books.entity.Book;

@Repository
public interface AuthorRepository extends JpaRepository<Book, Long>{
	
}
