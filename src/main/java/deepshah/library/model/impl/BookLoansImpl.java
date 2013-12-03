package deepshah.library.model.impl;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import deepshah.library.model.BookLoans;

@Entity
@Table(name = "book_loans")
@NamedQueries({ @NamedQuery(name = "fetchAllIssuedBooksQuery", query = "SELECT b FROM BookLoansImpl b")
})

public class BookLoansImpl implements BookLoans, Serializable {
	
	
	/**
	 * 
	 */
	
	private Date today_date = new java.sql.Date(new java.util.Date().getTime());
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "book_id", nullable = false, length = 15)
	@NotNull 
	@Size(min=1,max=15, message="Book ID cannot be empty and should be less than 15")
	private String book_id;

	@Id
	@Column(name = "branch_id", nullable = false)
	@NotNull
	@NumberFormat(style=Style.NUMBER)
	@Min(1)
	@Max(5)
	private int branch_id = 1;

	@Id
	@Column(name = "card_no", nullable = false, length = 10)
	@NotNull @Size(min=1,max=10, message="Card No cannot be empty and should be less than 10")
	private String card_no;

	@Column(name = "date_out")
	private Date date_out = today_date; 

	@Column(name = "due_date")
	private Date due_date ;//= addDay(today_date,14);
	
	
	@ManyToOne(targetEntity = BookImpl.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id", referencedColumnName = "book_id")
	private BookImpl book_bookloans;

	@ManyToOne(targetEntity = LibraryBranchImpl.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
	private LibraryBranchImpl library_bookloans;

	@ManyToOne(targetEntity = BorrowerImpl.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "card_no", referencedColumnName = "card_no")
	private BorrowerImpl borrower_bookloans;

	
//
//	public java.sql.Date addDays(Date givenDate,int days){
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(givenDate);
//		 cal.add(Calendar.DATE, days);
//	//	long newDate = new Date(cal.getTime().getTime());
//
//	//	System.out.println(newDate);
//	//	return new java.sql.Date(newDate);
//	}
	
	@Override
	public String getBook_id() {
		return book_id;
	}

	@Override
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	@Override
	public int getBranch_id() {
		return branch_id;
	}

	@Override
	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}

	@Override
	public String getCard_no() {
		return card_no;
	}

	@Override
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	@Override
	public Date getDate_out() {
		return date_out;
	}

	@Override
	public void setDate_out(Date date_out) {
		this.date_out = date_out;
	}

	@Override
	public Date getDue_date() {
		return due_date;
	}

	@Override
	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}

	
	/**
	 * @param book_id
	 * @param branch_id
	 * @param card_no
	 * @param date_out
	 * @param due_date
	 */
	public BookLoansImpl(String book_id, int branch_id, String card_no,
			Date date_out, Date due_date) {
		super();
		this.book_id = book_id;
		this.branch_id = branch_id;
		this.card_no = card_no;
		this.date_out = date_out;
		this.due_date = due_date;
	}

	
	public BookImpl getBook_bookloans() {
		return book_bookloans;
	}

	public void setBook_bookloans(BookImpl book_bookloans) {
		this.book_bookloans = book_bookloans;
	}

	public LibraryBranchImpl getLibrary_bookloans() {
		return library_bookloans;
	}

	public void setLibrary_bookloans(LibraryBranchImpl library_bookloans) {
		this.library_bookloans = library_bookloans;
	}

	public BorrowerImpl getBorrower_bookloans() {
		return borrower_bookloans;
	}

	public void setBorrower_bookloans(BorrowerImpl borrower_bookloans) {
		this.borrower_bookloans = borrower_bookloans;
	}

	/**
	 * 
	 */
	public BookLoansImpl() {
		}

}
