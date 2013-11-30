package deepshah.library.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import deepshah.library.dao.BookDAO;
import deepshah.library.model.Book;
import deepshah.library.model.impl.BookImpl;

@Repository
public class BookDAOImpl implements BookDAO{

	
	@PersistenceContext(unitName = "companySetup")
	private EntityManager entityManager;

	@Override
	@Transactional(value = "transactionManager", readOnly = false)
	public void insert(Book book) {
		entityManager.persist(book);
	}
	
	@Override
	@Transactional(value = "transactionManager", readOnly = false)
	public Book update(Book book) {
		return entityManager.merge(book);
	}
	
	@Override
	@Transactional(value = "transactionManager", readOnly = false)
	public void remove(Book book) {
		entityManager.remove(book);
	}
	
	@Override
	public Book find(Book book) {
		return entityManager.find(BookImpl.class,book.getBook_id());
	}
	
	@Override
	public boolean isExists(Book book) {
		return entityManager.contains(book);
	}
	
	@Override
	public boolean isExists(String bookId) {
		Book b = entityManager.find(BookImpl.class,bookId);
		if(b==null)
			return false;
		return true;
	}
	
	
	
	
}
