package com.springboot.book_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.book_app.dto.MessageDto;
import com.springboot.book_app.exception.InvalidIdException;
import com.springboot.book_app.model.Book;
import com.springboot.book_app.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@PostMapping("/add")
	public Book addBook(@RequestBody Book book) {
		return bookService.addBook(book);
	}
	
	@GetMapping("/getAll")
	public List<Book> getAllBooks(){
		return bookService.getAll();
	}
	
	@GetMapping("/{isbn}")
	public ResponseEntity<?> getBookByISBN(@PathVariable String isbn, MessageDto dto) {
		try {
			Book book = bookService.getBookByISBN(isbn);
			return ResponseEntity.ok(book);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateBook(@PathVariable int id,@RequestBody Book book, MessageDto dto) {
		try {
			book = bookService.updateBook(id,book);
			return ResponseEntity.ok(book);
			
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@DeleteMapping("/delete/{isbn}")
	public ResponseEntity<?> deleteBook(@PathVariable String isbn, MessageDto dto) {
		try {
			bookService.deleteBook(isbn);
			dto.setMsg("Book with isbn "+isbn+" is deleted");
			return ResponseEntity.ok(dto);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	

}
