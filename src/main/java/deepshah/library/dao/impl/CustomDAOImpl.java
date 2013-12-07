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
	 public List<Object[]> getByIdTitleAuthor(String book_id,String title,String book_author)  {
Query query = entityManager.createNativeQuery("SELECT a.book_id,a.title,r.branch_id,r.branch_name,a.no_of_copies,a.num_out,a.num_avail "
		+ "FROM library_branch AS r, "
		+ "(SELECT c.book_id,c.title,c.branch_id,c.no_of_copies, COUNT(card_no) as num_out, (c.no_of_copies- COUNT(card_no)) as num_avail "
		+ "FROM  (SELECT * FROM book NATURAL JOIN book_copies NATURAL JOIN book_authors WHERE (book_id LIKE '%"+book_id+"%') AND (title LIKE '%"+title+"%') AND (author_name LIKE '%"+book_author+"%')) as c  "
		+ "LEFT JOIN "
		+ "(SELECT * FROM book NATURAL JOIN book_loans NATURAL JOIN book_authors WHERE (book_id LIKE '%"+book_id+"%') AND (title LIKE '%"+title+"%') AND (author_name LIKE '%"+book_author+"%')) as b "
		+ "ON c.branch_id = b.branch_id "
		+ "GROUP BY c.book_id,c.branch_id) AS a "
		+ "WHERE r.branch_id=a.branch_id "
		+ "ORDER BY r.branch_id");
	List<Object[]> result = query.getResultList();
	 return result;
	 }
	
	
	 
	 @Override
	 public int getBookAvailabilityInBranch(String ofBookId,String inBranch) {
		 Query query = entityManager.createNativeQuery("SELECT a.num_avail "
					+ "FROM library_branch AS r, "
					+ "(SELECT c.title,c.branch_id,c.no_of_copies, COUNT(card_no) as num_out, (c.no_of_copies- COUNT(card_no)) as num_avail"
					+ " FROM  (SELECT * FROM book NATURAL JOIN book_copies WHERE book_id = '"+ofBookId+"' and branch_id='"+inBranch+"') as c "
					+ " LEFT JOIN "
					+ "(SELECT * FROM book NATURAL JOIN book_loans WHERE book_id = '"+ofBookId+"' and branch_id='"+inBranch+"') as b "
					+ "ON c.branch_id = b.branch_id GROUP BY c.branch_id) AS a "
					+ "WHERE r.branch_id = a.branch_id");
	 List result = query.getResultList();
	 if(result == null)
		 return 0;
	 int bookleft = Integer.valueOf(result.get(0).toString());
	 	return bookleft;
	 }
	 @Override
	 public List<Object[]> getIssuedBook(String bookId,String cardNo,String fName,String lName){
		 Query query = entityManager.createNativeQuery("select a.book_id,a.branch_id,a.card_no,b.fname,b.lname,a.date_out,a.due_date "
		 		+ "from book_loans as a,borrower as b "
		 		+ "WHERE a.card_no=b.card_no AND b.lname LIKE '%"+lName+"%' AND b.fname LIKE '%"+fName+"%' AND b.card_no LIKE '%"+cardNo+"%' AND a.book_id LIKE '%"+bookId+"%'");
	 List<Object[]> result = query.getResultList();
	 return result;
	 }
}
