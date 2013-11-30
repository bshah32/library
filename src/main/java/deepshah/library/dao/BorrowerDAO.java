package deepshah.library.dao;

import java.sql.SQLException;

import deepshah.library.model.Borrower;

public interface BorrowerDAO {

	Borrower find(String card_no);

	Borrower update(Borrower borrower);

	boolean isExist(String card_no);

	boolean isExist(Borrower borrower);

	boolean insert(Borrower borrower);

	void remove(String card_no);

	boolean matchBorrower(Borrower borrower);

//	Borrower selectABorrower(Borrower borrower);

}
