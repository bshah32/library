package deepshah.library.model.impl;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import deepshah.library.model.BookLoans;

@Entity
@Table(name = "book_loans")
@NamedQueries({ @NamedQuery(name = "fetchAllIssuedBooksQuery", query = "SELECT b FROM BookLoansImpl b")
})

public class BookLoansImpl implements BookLoans, Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "book_id", nullable = false, length = 15)
	private String book_id;

	@Id
	@Column(name = "branch_id", nullable = false)
	private int branch_id = 1;

	@Id
	@Column(name = "card_no", nullable = false, length = 10)
	private String card_no;

	@Column(name = "date_out")
	private Date date_out; // = new java.sql.Date(new java.util.Date().getTime()); 

	@Column(name = "due_date")
	private Date due_date; // = new java.sql.Date(new java.util.Date().getTime());
	
	@ManyToOne(targetEntity = BookImpl.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id", referencedColumnName = "book_id")
	private BookImpl book_bookloans;

	@ManyToOne(targetEntity = LibraryBranchImpl.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
	private LibraryBranchImpl library_bookloans;

	@ManyToOne(targetEntity = BorrowerImpl.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "card_no", referencedColumnName = "card_no")
	private BorrowerImpl borrower_bookloans;

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

	/**
	 * 
	 */
	public BookLoansImpl() {
		}

}
