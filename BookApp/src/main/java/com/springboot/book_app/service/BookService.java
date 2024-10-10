package com.springboot.book_app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.book_app.exception.InvalidIdException;
import com.springboot.book_app.exception.InvalidInputException;
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

	public Page<Book> getAll(Pageable pageable) {
	    return bookRepo.findAll(pageable);
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

	public void validate(Book book) throws InvalidInputException {
		if(book.getCategory().toString()==""|| book.getCategory().toString()==null) {
			throw new InvalidInputException("Category cannot be null/blank");
		}
		if(book.getDescription()==""||book.getDescription()==null) {
			throw new InvalidInputException("Description cannot be null/blank");
		}
		if(book.getTitle()==""||book.getTitle()==null) {
			throw new InvalidInputException("Title cannot be null/blank");
		}
		if(book.getPrice()<=0) {
			throw new InvalidInputException("Price cannot be 0/negative");
		}
		if(book.getQty()<0) {
			throw new InvalidInputException("Quantity cannot be negative");
			
		}
		
	}

	public String getDescription(int id) throws InvalidIdException {
		Optional<Book> optional = bookRepo.findById(id);
		if(optional.isEmpty()) {
			throw new InvalidIdException("Invalid ID");
		}
		Book book = optional.get();
		String description = bookRepo.findDescription(id);
		return description;		
	}

	
}
