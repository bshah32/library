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
Query query = entityManager.createNativeQuery("select Q.b_id,Q.title as title,Q.id_branch,Q.b_name as branch_name, Q.copies as no_of_copies, ifnull(R.issued,0) as num_out,  (select(no_of_copies - num_out)) as num_avail "
+ "from (select branch_id as id_branch,book_id as b_id,  count(*) as issued "
+ "from book_loans group by branch_id,book_id) as R "
+ "right outer join "
+ "(select T1.title as title,S.branch_id as id_branch,S.book_id as b_id,S.no_of_copies as copies,T.branch_name as b_name "
+ "from (select b.book_id, title "
+ "from book as b,book_authors as a "
+ "where b.book_id=a.book_id AND b.title LIKE '%"+title+"%' AND b.book_id LIKE '%"+book_id+"%' AND a.author_name LIKE '%"+book_author+"%') as T1,book_copies as S,library_branch as T "
+ "where T1.book_id = S.book_id and S.branch_id = T.branch_id "
+ "group by S.book_id, S.branch_id) as Q on Q.b_id = R.b_id and Q.id_branch = R.id_branch "
+ "ORDER BY Q.b_id,Q.id_branch");
	List<Object[]> result = query.getResultList();
	 return result;
	 }
	
	
	 
	 @Override
	 public int getBookAvailabilityInBranch(String ofBookId,String inBranch) {

		 Query query = entityManager.createNativeQuery("select (select(Q.copies - ifnull(R.issued,0))) as num_avail "
				 + "from (select branch_id as id_branch,book_id as b_id,  count(*) as issued "
				 + "from book_loans group by branch_id,book_id) as R "
				 + "right outer join "
				 + "(select T1.title as title,S.branch_id as id_branch,S.book_id as b_id,S.no_of_copies as copies,T.branch_name as b_name "
				 + "from (select b.book_id, title "
				 + "from book as b,book_authors as a "
				 + "where b.book_id=a.book_id AND b.book_id = '"+ofBookId+"') as T1,book_copies as S,library_branch as T "
				 + "where T1.book_id = S.book_id and S.branch_id = T.branch_id AND S.branch_id='"+inBranch+"' "
				 + "group by S.book_id, S.branch_id) as Q on Q.b_id = R.b_id and Q.id_branch = R.id_branch "
				 + "ORDER BY Q.b_id,Q.id_branch");
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
