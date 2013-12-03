package deepshah.library.dao;

import java.sql.SQLException;
import java.util.List;

import deepshah.library.jspmodels.BookLoanBorrowerRelation;

public interface CustomDAO {

	
	int getBookAvailabilityInBranch(String ofBook, String inBranch);

	List<Object[]> getIssuedBook(String bookId, String cardNo, String fName,
			String lName);

	List<Object[]> getByIdTitleAuthor(String book_id, String title,
			String book_author);

}
