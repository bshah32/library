package deepshah.library.jspmodels;

import java.util.Date;

public class BookLoanBorrowerRelation {

	private String book_id;
	
	private String branch_id;
	
	private int card_no;
	
	private String fname;
	
	private String lname;
	
	private Date date_out;
	
	private Date due_date;

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}


	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public Date getDate_out() {
		return date_out;
	}

	public void setDate_out(Date date_out) {
		this.date_out = date_out;
	}

	public Date getDue_date() {
		return due_date;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}

	public String toString(){
		return getBook_id()+":"+getFname()+":"+getLname()+":"+getDate_out()+":"+getDue_date(); 
	}

	public String getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}

	public int getCard_no() {
		return card_no;
	}

	public void setCard_no(int card_no) {
		this.card_no = card_no;
	}
	
	
}
