package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.entity.BookEntity;



@Repository
public class BookDaoImpl implements BookDao {
		
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<BookEntity> searchCriteria(String authname, Integer min, Integer max) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<BookEntity> cq = cb.createQuery(BookEntity.class);
		
        Root<BookEntity> book = cq.from(BookEntity.class);
        Predicate authnamePredicate = cb.equal(book.get("authname"),authname);
        Predicate pricePredicate= cb.between(book.get("price"),min, max);
        
        cq.where(authnamePredicate, pricePredicate);

        TypedQuery<BookEntity> query = entityManager.createQuery(cq);
        return query.getResultList();
	}
	
	
}
