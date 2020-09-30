package com.openlibrary.all.books.service;

import java.util.List;

import com.openlibrary.all.books.controller.BookModel;


public interface LibraryCrudService<T> {
	
	public T create(T modelObject);

	public void update(T modelObject);

	public void delete(T modelObject);

	public List<T> find(T modelObject);

	public boolean isExists(T modelObject);
	
	public List<T>  list();

	T findOne(T obj);
	

}
