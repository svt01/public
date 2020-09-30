package com.openlibrary.all.books.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openlibrary.all.books.controller.BookModel;
import com.openlibrary.all.books.entity.Book;
import com.openlibrary.all.books.service.BookService;
import com.openlibrary.all.books.service.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	private static Logger LOGGER = LoggerFactory
			.getLogger(BookServiceImpl.class);

	@Override
	public BookModel create(BookModel book) {
		LOGGER.info("In createBook");
		Book entity = populateBookEntity(new Book(), book);
		Book save = bookRepository.save(entity);
		return populateEntityToModel(save);
	}

	@Override
	public void update(BookModel book) {
		LOGGER.info("In updateBook");
		Book entity = bookRepository.getOne(Long.valueOf(book.getId()));
		populateBookEntity(entity, book);
		bookRepository.saveAndFlush(entity);

	}

	@Override
	public void delete(BookModel book) {
		LOGGER.info("In updateBook");
		bookRepository.deleteById(Long.valueOf(book.getId()));

	}

	@Override
	public List<BookModel> find(BookModel book) {
		LOGGER.info("In findBook");
		Book one = bookRepository.getOne(Long.parseLong(book.getId()));
		System.out.println("Retrieved book : " + one); 
		return Arrays.asList(populateEntityToModel(one));
	}

	@Override
	public List<BookModel> list() {

		List<BookModel> bookLst = new ArrayList<>();
		List<Book> findAll = bookRepository.findAll();
		for (Book book : findAll) {
			bookLst.add(populateEntityToModel(book));
		}
		return bookLst;
	}

	@Override
	public boolean isExists(BookModel book) {
		LOGGER.info("In isLogBookExists");
		//TODO 
		// establish db connections
		// 
		// search the book by name
		return false;
	}

	private Book populateBookEntity(Book bookEntity, BookModel book) {
		bookEntity.setBookName(book.getBookName());
		bookEntity.setAuthor(book.getAuthor());
		bookEntity.setUser(book.getUser());
		return bookEntity;
	}

	private BookModel populateEntityToModel(Book book) {
		BookModel model = new BookModel();
		model.setId("" + book.getId());
		model.setAuthor(book.getAuthor());
		model.setBookName(book.getBookName());
		model.setUser(book.getUser());
		
		return model;
	}

	@Override
	public BookModel findOne(BookModel book) {
		LOGGER.info("In findBook");
		Book one = bookRepository.getOne(Long.parseLong(book.getId()));
		return (populateEntityToModel(one));
	}

}
