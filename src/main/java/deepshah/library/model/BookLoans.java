package deepshah.library.model;

import java.sql.Date;

public interface BookLoans {

	public Date getDue_date();

	public Date getDate_out();

	public String getCard_no();

	public int getBranch_id();

	public String getBook_id();

	
}
