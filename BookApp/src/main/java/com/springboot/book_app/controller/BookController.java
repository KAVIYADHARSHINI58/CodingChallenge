package com.springboot.book_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.book_app.dto.MessageDto;
import com.springboot.book_app.enums.Category;
import com.springboot.book_app.exception.InvalidIdException;
import com.springboot.book_app.exception.InvalidInputException;
import com.springboot.book_app.model.Book;
import com.springboot.book_app.service.BookService;

@RestController
@RequestMapping("/book")
@CrossOrigin(origins = {"http://localhost:4200"})
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addBook(@RequestBody Book book) {
		try {
			bookService.validate(book);
			return ResponseEntity.ok(bookService.add(book));
		} catch (InvalidInputException e) {
			 return ResponseEntity.status(e.getStatusCode()).body(e.getMessage()); 
		}
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
	public Page<Book> getAll(@RequestParam(defaultValue ="0", required=false) Integer page,
			                 @RequestParam(defaultValue = "1000", required=false) Integer size){
			                	 
	    Pageable pageable = PageRequest.of(page, size);	
		return bookService.getAll(pageable);
	}
	
	@GetMapping("/categories")
	public List<Category> getAllDays(){
		return List.of(Category.values());
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
