package com.springboot.book_app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.springboot.book_app.model.Book;

import jakarta.transaction.Transactional;

public interface BookRepo extends JpaRepository<Book, Integer>{

	@Query("select b.description from Book b where b.id=?1 ")
	String findDescription(int id);

  

}
