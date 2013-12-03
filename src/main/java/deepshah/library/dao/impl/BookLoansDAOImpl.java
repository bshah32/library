package deepshah.library.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import deepshah.library.dao.BookLoansDAO;
import deepshah.library.model.BookLoans;
import deepshah.library.model.impl.BookLoansImpl;

@Repository
public class BookLoansDAOImpl implements BookLoansDAO{
	
	@PersistenceContext(unitName = "companySetup")
	private EntityManager entityManager;

	@Override
	@Transactional(value = "transactionManager", readOnly = false)
	public boolean insert(BookLoans book) {
		try
		{
		if (!isExist(book)) {
			 Query query = entityManager.createNativeQuery("insert into book_loans (book_id, card_no, date_out, due_date, branch_id) values (:bookId, :cardNo,DATE_ADD(CURDATE(),INTERVAL 14 DAY), :dueDate,:branchId)",BookLoansImpl.class);
			query.setParameter("bookId", book.getBook_id());
			query.setParameter("branchId",book.getBranch_id());
			query.setParameter("cardNo",book.getCard_no());
			query.setParameter("dueDate",book.getDue_date());
				query.executeUpdate();
				return true;
			}
		}
			catch(Exception e){
				return false;
			}
		return false;
	}
	
	@Override
	@Transactional(value = "transactionManager", readOnly = false)
	public boolean isExist(BookLoans book) {
		BookLoans foundBook = entityManager.find(BookLoansImpl.class, book);
		if (foundBook != null){
			return true;
		}
		return false;
	}	
	
	@Override
	@Transactional(value = "transactionManager", readOnly = false)
	public void remove(BookLoans book) {
		if (this.isExist(book)) {
		entityManager.remove(book);
			}
		}
		
		@Override
		@Transactional(value = "transactionManager", readOnly = false)
		public BookLoans getIssuedBookInfo(BookLoans book) {
		return entityManager.find(BookLoansImpl.class, book);	
		}
		
		 @Override
		 public List<BookLoans> getAllIssuedBooks() {
		 TypedQuery<BookLoansImpl> query = entityManager.createNamedQuery
		 ("fetchAllIssuedBooksQuery", BookLoansImpl.class);
		 List issuedBooks = query.getResultList();
		 return issuedBooks;
		 }
	
}
