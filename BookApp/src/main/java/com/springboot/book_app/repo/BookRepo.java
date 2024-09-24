package com.springboot.book_app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.springboot.book_app.model.Book;

import jakarta.transaction.Transactional;

public interface BookRepo extends JpaRepository<Book, Integer>{

	
	@Query("select b from Book b where b.isbn=?1")
	Optional<Book> getBookByISBN(String isbn);

	@Modifying
	@Transactional
	@Query("delete from Book b where b.isbn=?1")
	void deleteByISBN(String isbn);

}
