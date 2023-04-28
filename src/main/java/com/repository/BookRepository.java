package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entity.BookEntity;


@Repository
public interface BookRepository extends CrudRepository<BookEntity, Integer> {
		
	List<BookEntity> findAll();
	Optional<BookEntity> findByName(String name);
	Optional<BookEntity> findByAuthname(String authname);
	
	List<BookEntity> findByPriceBetween(float min, float max);
	List<BookEntity> findAllByAvailableind(boolean b);
	
}
