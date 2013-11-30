package deepshah.library.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import deepshah.library.dao.CustomDAO;


@Repository
public class CustomDAOImpl implements CustomDAO{

	@PersistenceContext(unitName = "companySetup")
	private EntityManager entityManager;

	 @Override
	 public List<Object[]> getByTitle(String title) {
		 Query query = entityManager.createNativeQuery("SELECT a.title,r.branch_id,r.branch_name,a.no_of_copies,a.num_out,a.num_avail "
					+ "FROM library_branch AS r, "
					+ "(SELECT c.title,c.branch_id,c.no_of_copies, COUNT(card_no) as num_out, (c.no_of_copies- COUNT(card_no)) as num_avail"
					+ " FROM  (SELECT * FROM book NATURAL JOIN book_copies WHERE title LIKE '%"+title+"%') as c "
					+ " LEFT JOIN "
					+ "(SELECT * FROM book NATURAL JOIN book_loans WHERE title LIKE '%"+title+"%') as b "
					+ "ON c.branch_id = b.branch_id GROUP BY c.branch_id) AS a "
					+ "WHERE r.branch_id = a.branch_id");
	List<Object[]> result = query.getResultList();
	 return result;
	 }
	 
	 @Override
	 public List<Object[]> getById(String book_id) {
		 Query query = entityManager.createNativeQuery("SELECT a.title,r.branch_id,r.branch_name,a.no_of_copies,a.num_out,a.num_avail "
					+ "FROM library_branch AS r, "
					+ "(SELECT c.title,c.branch_id,c.no_of_copies, COUNT(card_no) as num_out, (c.no_of_copies- COUNT(card_no)) as num_avail"
					+ " FROM  (SELECT * FROM book NATURAL JOIN book_copies WHERE book_id LIKE '%"+book_id+"%') as c "
					+ " LEFT JOIN "
					+ "(SELECT * FROM book NATURAL JOIN book_loans WHERE book_id LIKE '%"+book_id+"%') as b "
					+ "ON c.branch_id = b.branch_id GROUP BY c.branch_id) AS a "
					+ "WHERE r.branch_id = a.branch_id");
	 List<Object[]> result = query.getResultList();
	 return result;
	 }
	 
	 @Override
	 public List<Object[]> getByIdAndTitle(String book_id,String title) {
		 Query query = entityManager.createNativeQuery("SELECT a.title,r.branch_id,r.branch_name,a.no_of_copies,a.num_out,a.num_avail "
					+ "FROM library_branch AS r, "
					+ "(SELECT c.title,c.branch_id,c.no_of_copies, COUNT(card_no) as num_out, (c.no_of_copies- COUNT(card_no)) as num_avail"
					+ " FROM  (SELECT * FROM book NATURAL JOIN book_copies WHERE title LIKE '%"+title+"%' && book_id LIKE '%"+book_id+"%') as c "
					+ " LEFT JOIN "
					+ "(SELECT * FROM book NATURAL JOIN book_loans WHERE title LIKE '%"+title+"%' && book_id LIKE '%"+book_id+"%') as b "
					+ "ON c.branch_id = b.branch_id GROUP BY c.branch_id) AS a "
					+ "WHERE r.branch_id = a.branch_id");
	List<Object[]> result = query.getResultList();
	 return result;
	 }
	 @Override
	 public List<Object[]> getByAuthor(String book_author) {
		 Query query = entityManager.createNativeQuery("SELECT a.title,r.branch_id,r.branch_name,a.no_of_copies,a.num_out,a.num_avail "
					+ "FROM library_branch AS r, "
					+ "(SELECT c.title,c.branch_id,c.no_of_copies, COUNT(card_no) as num_out, (c.no_of_copies- COUNT(card_no)) as num_avail"
					+ " FROM  (SELECT * FROM book NATURAL JOIN book_copies WHERE book_id LIKE '%"+book_author+"%') as c "
					+ " LEFT JOIN "
					+ "(SELECT * FROM book NATURAL JOIN book_loans WHERE book_id LIKE '%"+book_author+"%') as b "
					+ "ON c.branch_id = b.branch_id GROUP BY c.branch_id) AS a "
					+ "WHERE r.branch_id = a.branch_id");
	 List<Object[]> result = query.getResultList();
	 return result;
	 }
}
