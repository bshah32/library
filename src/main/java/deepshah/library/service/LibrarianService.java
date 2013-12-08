package deepshah.library.service;

import java.sql.SQLException;
import java.util.List;

import deepshah.library.jspmodels.BookLoanBorrowerRelation;
import deepshah.library.model.Book;
import deepshah.library.model.BookLoans;
import deepshah.library.model.Borrower;
import deepshah.library.model.LibraryBranch;

public interface LibrarianService {


	boolean checkinNewBook(BookLoans newBook);

	void checkoutOldBook(BookLoans oldBook);
	
	List<BookLoans> getAllIssuedCheckedinBooks();

	BookLoans getIssuedBook(BookLoans findBook);

	boolean newBorrower(Borrower new_borrower);

	Borrower updateBorrower(Borrower borrower);

	Borrower searchBorrower(String card_no);

	void removeBorrower(String card_no);

	int validIssueStatusOfBorrower(Borrower borrower);

	Borrower findBorrower(String card_no);

	boolean bookExists(String bookId);

	boolean branchExists(int branchId);
	
	boolean borrowerExists(String cardNo);

	boolean cheackBookAvailablility(String bookId, String branchId);

	List<Object[]> getIssuedBook(String bookId, String cardNo, String fName,
			String lName);

	List<Object[]> getBookAvailabilityByIdAndName(String book_id, String title,
			String author);

	boolean insertBranch(LibraryBranch branch);

	boolean searchBranch(LibraryBranch branch);

	LibraryBranch updateBranch(LibraryBranch branch);

	List<LibraryBranch> fetchAllBranches();

	boolean searchBranchByName(String branch_name);

	void deleteBranch(int branch_id);

	LibraryBranch searchBranch(int branchId);

	int getMaxCardNo();

	int getMaxBranchNo();

	List<LibraryBranch> searchSelectedBranch(LibraryBranch branch);

	List<Borrower> fetchAllBorrower();

	void deleteBorrower(String card_no);

	List<Book> fetchAllBooks();

	void deleteBook(String bookId);

	Book searchBook(String bookId);

	Book updateBook(Book book);
	
}
