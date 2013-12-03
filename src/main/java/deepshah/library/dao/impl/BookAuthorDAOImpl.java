package deepshah.library.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import deepshah.library.dao.BookAuthorDAO;
import deepshah.library.model.BookAuthors;
import deepshah.library.model.impl.BookAuthorsImpl;


public class BookAuthorDAOImpl implements BookAuthorDAO{

	
	@PersistenceContext(unitName = "companySetup")
	private EntityManager entityManager;


	@Override
	@Transactional(value = "transactionManager", readOnly = false)
	public void insert(BookAuthors author) {
		entityManager.persist(author);
	}
	
	@Override
	@Transactional(value = "transactionManager", readOnly = false)
	public BookAuthors update(BookAuthors author) {
		return entityManager.merge(author);
	}
	
	@Override
	@Transactional(value = "transactionManager", readOnly = false)
	public void remove(BookAuthors author) {
		entityManager.remove(author);
	}
	
	@Override
	public BookAuthorDAO find(String bookId,String authorName) {
		BookAuthors author = new BookAuthorsImpl(bookId,authorName);
	 	return (BookAuthorDAO) entityManager.find(BookAuthorsImpl.class,author);
	}
	
	@Override
	public boolean isExists(BookAuthors author) {
		return entityManager.contains(author);
	}
	
	@Override
	public boolean isExists(String bookId,String authorName) {
		BookAuthors author = new BookAuthorsImpl(bookId,authorName);
		return entityManager.contains(author);
	}
	
	
	
}
