package com.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.BookDaoImpl;
import com.entity.BookCustomResponse;
import com.entity.BookEntity;
import com.repository.BookRepository;

@CrossOrigin
@RestController
public class BookController {
	
	@Autowired
	BookRepository bookRepo;
	
	@Autowired
	BookDaoImpl bookDaoImpl;

	
	@PostMapping("/addBook")
	public ResponseEntity<BookCustomResponse<BookEntity>> addBook(@RequestBody BookEntity book) {
		Optional<BookEntity> opt1 = bookRepo.findByName(book.getName());
		Optional<BookEntity> opt2 = bookRepo.findByAuthname(book.getAuthname());
		if(opt1.isEmpty() || opt2.isEmpty()) {
				bookRepo.save(book);
				BookCustomResponse<BookEntity> resp = new BookCustomResponse();
				
				resp.setData(book);
				resp.setMsg("Book Added Successfully!");
				return ResponseEntity.ok(resp);
		}
		else {
			BookCustomResponse<BookEntity> resp = new BookCustomResponse();
			resp.setData(book);
			resp.setMsg("Book ALready Exist");
			
			return ResponseEntity.unprocessableEntity().body(resp);
		}
	}
	
	@GetMapping("/getAllBooks")
	public ResponseEntity<BookCustomResponse<List<BookEntity>>> getAllBooks(){
		
		List<BookEntity> books = bookRepo.findAll();
		
		BookCustomResponse<List<BookEntity>> resp = new BookCustomResponse<>();
		resp.setData(books);
		resp.setMsg("get all books");
		
		
		return ResponseEntity.ok(resp);
	}
	
	@GetMapping("/bookById/{bid}")
	public BookEntity getBookById(@PathVariable("bid") Integer bid) {
		Optional<BookEntity> proOptional = bookRepo.findById(bid);
		if(proOptional.isEmpty()) {
			return null;
		}
		else {
			return proOptional.get();
		}
	}
	
	@DeleteMapping("/deleteBook/{bid}")
	public BookEntity deleteBookById(@PathVariable("bid")Integer bid) {
		
		
		Optional<BookEntity> proOptional = bookRepo.findById(bid);
		BookCustomResponse<BookEntity> resp = new BookCustomResponse<>();
		if(proOptional.isEmpty()) {
			return null;
		}
		else {
			BookEntity bookEntity = proOptional.get();
			bookRepo.deleteById(bid);
			return bookEntity;
		}
	}
	
	@PutMapping("/updateBook")
	public BookEntity updateUser(@RequestBody BookEntity book) {
		bookRepo.save(book);
		return book;
	}
	
	@GetMapping("/searchByRange/{min}/{max}")
	public ResponseEntity<List<BookEntity>> searchByRange(@PathVariable("min")float min, @PathVariable("max")float max){
		List<BookEntity> book = bookRepo.findByPriceBetween(min,max);
		return ResponseEntity.ok(book);
	}
	
	
	@GetMapping("/book/available/{availableind}")
	public ResponseEntity<List<BookEntity>> bookAvalability(@PathVariable boolean availableind) {
		
		List<BookEntity> bookList = bookRepo.findAllByAvailableind(availableind);
		
		return ResponseEntity.ok(bookList);
		
	}
	
	@GetMapping("/search/{authname}/{min}/{max}")
	public ResponseEntity<List<BookEntity>> searchByCriteria(@PathVariable("authname") String authname, @PathVariable("min") Integer min, @PathVariable("max") Integer max)
	{
		List<BookEntity> book=bookDaoImpl.searchCriteria(authname, min, max);
		return ResponseEntity.ok(book);
	}

}
