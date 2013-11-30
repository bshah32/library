package deepshah.library.model;

import java.sql.Date;


public interface BookLoans {

	public Date getDue_date();

	public Date getDate_out();

	public String getCard_no();

	public int getBranch_id();

	public String getBook_id();

	void setBook_id(String book_id);

	void setBranch_id(int branch_id);

	void setCard_no(String card_no);

	void setDate_out(Date date_out);

	void setDue_date(Date due_date);

}
