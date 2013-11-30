package deepshah.library.model;

import java.util.Set;

import deepshah.library.model.impl.BookLoansImpl;

public interface Borrower {

	public String getCard_no();

	public String getPhone();

	public String getAddress();

	public String getLname();

	public String getFname();

	void setPhone(String phone);

	void setLname(String lname);

	void setCard_no(String card_no);

}
