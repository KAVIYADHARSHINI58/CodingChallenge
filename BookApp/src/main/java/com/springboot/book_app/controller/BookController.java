package com.springboot.book_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = {"http://localhost:4200"})
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@PostMapping("/add")
	public Book addBook(@RequestBody Book book) {
		return bookService.add(book);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<?> findBook(@PathVariable int id, MessageDto dto){
		try {
			Book book = bookService.findBook(id);
			return ResponseEntity.ok(book);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@GetMapping("/find/all")
	public List<Book> getAll(){
		return bookService.getAll();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable int id, MessageDto dto) {
		try {
			bookService.deleteBook(id);
			dto.setMsg("Book deleted");
			return ResponseEntity.ok(dto);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@PutMapping("/edit/{id}")
	public ResponseEntity<?> editBook(@PathVariable int id, @RequestBody Book book, MessageDto dto){
		try {
			book = bookService.editBook(id,book);
			return ResponseEntity.ok(book);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
}
