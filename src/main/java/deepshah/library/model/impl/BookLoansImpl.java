package deepshah.library.model.impl;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import deepshah.library.model.BookLoans;


@Entity
@Table(name="book_loans")
public class BookLoansImpl implements BookLoans,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
    @Column(name="book_id", nullable=false,length=15)
	private String book_id;
 
	@Id 
    @Column(name="branch_id", nullable=false)
	private int branch_id = 0;
 
	@Id 
    @Column(name="card_no", nullable=false,length=10)
	private String card_no;
 
	@ManyToOne(targetEntity = BookImpl.class, fetch = FetchType.LAZY)
	@JoinColumn(name="book_id",referencedColumnName = "book_id")
	private BookImpl book_bookloans;
	
	
	@ManyToOne(targetEntity = LibraryBranchImpl.class, fetch = FetchType.LAZY)
	@JoinColumn(name="branch_id",referencedColumnName = "branch_id")
	private LibraryBranchImpl library_bookloans;
	
	
	@ManyToOne(targetEntity = BorrowerImpl.class, fetch = FetchType.LAZY)
	@JoinColumn(name="card_no", referencedColumnName = "card_no")
	private BorrowerImpl borrower_bookloans;
	
	@Column(name="date_out")
	private Date date_out;
	
	@Column(name="due_date")
	private Date due_date;

	@Override
	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	@Override
	public int getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}

	@Override
	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	@Override
	public Date getDate_out() {
		return date_out;
	}

	public void setDate_out(Date date_out) {
		this.date_out = date_out;
	}

	@Override
	public Date getDue_date() {
		return due_date;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book_id == null) ? 0 : book_id.hashCode());
		result = prime * result + branch_id;
		result = prime * result + ((card_no == null) ? 0 : card_no.hashCode());
		result = prime * result
				+ ((date_out == null) ? 0 : date_out.hashCode());
		result = prime * result
				+ ((due_date == null) ? 0 : due_date.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookLoansImpl other = (BookLoansImpl) obj;
		if (book_id == null) {
			if (other.book_id != null)
				return false;
		} else if (!book_id.equals(other.book_id))
			return false;
		if (branch_id != other.branch_id)
			return false;
		if (card_no == null) {
			if (other.card_no != null)
				return false;
		} else if (!card_no.equals(other.card_no))
			return false;
		if (date_out == null) {
			if (other.date_out != null)
				return false;
		} else if (!date_out.equals(other.date_out))
			return false;
		if (due_date == null) {
			if (other.due_date != null)
				return false;
		} else if (!due_date.equals(other.due_date))
			return false;
		return true;
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
		super();
	}
	
	
	
	
	
	
	
}
