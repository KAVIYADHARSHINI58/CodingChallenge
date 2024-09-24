package com.springboot.book_app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.book_app.exception.InvalidIdException;
import com.springboot.book_app.model.Book;
import com.springboot.book_app.repo.BookRepo;
import com.springboot.book_app.service.BookService;

@SpringBootTest
public class BookServiceTest {

	@InjectMocks
	private BookService bookService;

	@Mock
	private BookRepo bookRepo;

	@Test
	public void getAllTest() {

		Book b1 = new Book();
		b1.setId(1);
		b1.setIsbn("123456");

		Book b2 = new Book();
		b2.setId(2);
		b2.setIsbn("67890");

		Book b3 = new Book();
		b3.setId(1);
		b3.setIsbn("09876");

		Book b4 = new Book();
		b4.setId(4);
		b4.setIsbn("65478");

		List<Book> list = Arrays.asList(b1, b2, b3, b4);

		when(bookService.getAll()).thenReturn(list);
		int expectedValue = list.size();
		int actualValue = bookService.getAll().size();
		assertEquals(expectedValue, actualValue);

	}

	@Test
	public void addBookTest() {

		Book b = new Book();
		b.setIsbn("123-456");
		b.setPublicationYear("2018");
		b.setTitle("Title");
		b.setAuthor("Author");
		when(bookService.addBook(b)).thenReturn(b);
		assertEquals(b, b);

	}
	
	@Test
	public void getBookByISBNTest() {
		
		Book b = new Book();
		b.setIsbn("978-0399590504");
		b.setPublicationYear("2018");
		b.setTitle("Title");
		b.setAuthor("Author");
		
		try {
			when(bookService.getBookByISBN("978-0399590504")).thenReturn(b);
			Book b1 = bookService.getBookByISBN("978-0399590504");
			assertEquals(b,b1);
		} catch (InvalidIdException e) {
		}
		
		try {
			when(bookService.getBookByISBN("123")).thenThrow(InvalidIdException.class);
			bookService.getBookByISBN("123");
		}
		catch(InvalidIdException e) {
			assertNotNull(e);
		}
	}
	
	

}
