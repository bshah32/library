package deepshah.library.service;

import java.util.List;

import deepshah.library.model.BookLoans;
import deepshah.library.model.Borrower;

public interface LibrarianService {

	List<Object[]> getBookAvailabilityByName(String book_title);

	List<Object[]> getBookAvailabilityById(String book_id);

	List<Object[]> getBookAvailabilityByAuthor(String book_author);

	List<Object[]> getBookAvailabilityByIdAndName(String book_id, String title);

	boolean checkinNewBook(BookLoans newBook);

	void checkoutOldBook(BookLoans oldBook);
	
	List<BookLoans> getAllIssuedCheckedinBooks();

	BookLoans getIssuedBook(BookLoans findBook);

	boolean newBorrower(Borrower new_borrower);

	Borrower updateBorrower(Borrower borrower);

	Borrower searchBorrower(String card_no);

	void removeBorrower(String card_no);
	

}
