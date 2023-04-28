package com.dao;

import java.util.List;
import com.entity.BookEntity;

public interface BookDao {

	List<BookEntity> searchCriteria(String authname, Integer min, Integer max);
	
}