package deepshah.library.dao;

import java.util.List;

import deepshah.library.model.BookLoans;

public interface BookLoansDAO {

	boolean isExist(BookLoans book);

	boolean insert(BookLoans book);

	void remove(BookLoans book);

	BookLoans getIssuedBookInfo(BookLoans book);

	List<BookLoans> getAllIssuedBooks();

	
}
