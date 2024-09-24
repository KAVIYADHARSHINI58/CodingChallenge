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

	public Book addBook(Book book) {
		return bookRepo.save(book);
	}

	public List<Book> getAll() {
		return bookRepo.findAll();
	}

	public Book getBookByISBN(String isbn) throws InvalidIdException {
		Optional<Book> optional = bookRepo.getBookByISBN(isbn);
		if(optional.isEmpty()) {
			throw new InvalidIdException("Invalid ISBN");
		}
		Book book = optional.get();
		return book;
	}

	public Book updateBook(int id, Book book) throws InvalidIdException {
		Optional<Book> optional = bookRepo.findById(id);
		if(optional.isEmpty()) {
			throw new InvalidIdException("Invalid id");
		}
		Book newbook = optional.get();
		newbook.setAuthor(book.getAuthor());
		newbook.setTitle(book.getTitle());
		newbook.setIsbn(book.getIsbn());
		newbook.setPublicationYear(book.getPublicationYear());
		return bookRepo.save(newbook);		
	}

	public void deleteBook(String isbn) throws InvalidIdException {
		Optional<Book> optional = bookRepo.getBookByISBN(isbn);
		if(optional.isEmpty()) {
			throw new InvalidIdException("Invalid ISBN");
		}
		bookRepo.deleteByISBN(isbn);
	}

}
