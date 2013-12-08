package deepshah.library.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
	public Book update(Book tobook) {
		Book foundBook = entityManager.find(BookImpl.class,tobook.getBook_id());
		if(foundBook != null) {
			return entityManager.merge(tobook);
		}
		return null; 
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

	@Override
	public List<Book> getAllBooks() {
		TypedQuery<BookImpl> query = entityManager.createNamedQuery
		 ("fetchAllBooksQuery", BookImpl.class);
		 List books = query.getResultList();
		 return books;
	}
	
	@Override
	@Transactional(value = "transactionManager", readOnly = false)
	public void remove(String bookId) {
		Book book = entityManager.find(BookImpl.class, bookId);
		if (book != null) {
			entityManager.remove(book);
		}
	}

	@Override
	public Book findBook(String bookId) {
		 return entityManager.find(BookImpl.class, bookId);
	}
	
	
		
}
