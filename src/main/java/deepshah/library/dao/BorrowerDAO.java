package deepshah.library.dao;

import java.sql.SQLException;
import java.util.Set;

import deepshah.library.model.Borrower;
import deepshah.library.model.impl.BookLoansImpl;

public interface BorrowerDAO {

	Borrower find(String card_no);

	Borrower update(Borrower borrower);

	boolean isExist(String card_no);

	boolean isExist(Borrower borrower);

	boolean insert(Borrower borrower);

	void remove(String card_no);

	boolean matchBorrower(Borrower borrower);

	int getBookIssuedByUser(Borrower borrower);

	int countNoOfBookByBorrower(Borrower borrower);

	int fetchMaxCardId();

//	Borrower selectABorrower(Borrower borrower);

}
