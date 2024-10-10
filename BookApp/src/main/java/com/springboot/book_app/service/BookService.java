package com.springboot.book_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.book_app.exception.InvalidIdException;
import com.springboot.book_app.model.Book;
import com.springboot.book_app.repo.BookRepo;

@Service
public class BookService {
	
	@Autowired
	private BookRepo bookRepo;

	public Book add(Book book) {
		return bookRepo.save(book);
	}

	public Book findBook(int id) throws InvalidIdException {
		Optional<Book> optional = bookRepo.findById(id);
		if(optional.isEmpty()) {
			throw new InvalidIdException("Invalid ID");
		}
		Book book = optional.get();
		return book;
		
	}

	public List<Book> getAll() {
		return bookRepo.findAll();
	}

	public void deleteBook(int id) throws InvalidIdException {
		Optional<Book> optional = bookRepo.findById(id);
		if(optional.isEmpty()) {
			throw new InvalidIdException("Invalid ID");
		}
		Book book = optional.get();
		bookRepo.delete(book);	
	}

	public Book editBook(int id, Book book) throws InvalidIdException {
		Optional<Book> optional = bookRepo.findById(id);
		if(optional.isEmpty()) {
			throw new InvalidIdException("Invalid ID");
		}
		Book oldbook = optional.get();
		oldbook.setCategory(book.getCategory());
		oldbook.setDescription(book.getDescription());
		oldbook.setTitle(book.getTitle());
		oldbook.setPrice(book.getPrice());
		oldbook.setQty(book.getQty());
		return bookRepo.save(oldbook);
				
	}

	
}
