package deepshah.library.model.impl;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import deepshah.library.model.BookCopies;

@Entity
@Table(name = "book_copies")
public class BookCopiesImpl implements BookCopies, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "book_id", nullable = false, columnDefinition="varchar(15) default ''")
	private String book_id;

	@Id
	@Column(name = "branch_id", nullable = false,columnDefinition="int(5) default '0'")
	private int branch_id;

	@Column(name = "no_of_copies")
	private int no_of_copies;

	@ManyToOne(targetEntity = BookImpl.class, fetch = FetchType.LAZY,cascade={CascadeType.ALL})
	@JoinColumn(name = "book_id", referencedColumnName = "book_id")
	private BookImpl book_bookcopies;

	@ManyToOne(targetEntity = LibraryBranchImpl.class, fetch = FetchType.LAZY,cascade={CascadeType.ALL})
	@JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
	private LibraryBranchImpl librarybranch_bookcopies;

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
	public int getNo_of_copies() {
		return no_of_copies;
	}

	public void setNo_of_copies(int no_of_copies) {
		this.no_of_copies = no_of_copies;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book_id == null) ? 0 : book_id.hashCode());
		result = prime * result + branch_id;
		result = prime * result + no_of_copies;
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
		BookCopiesImpl other = (BookCopiesImpl) obj;
		if (book_id == null) {
			if (other.book_id != null)
				return false;
		} else if (!book_id.equals(other.book_id))
			return false;
		if (branch_id != other.branch_id)
			return false;
		if (no_of_copies != other.no_of_copies)
			return false;
		return true;
	}

	/**
	 * @param book_id
	 * @param branch_id
	 * @param no_of_copies
	 */
	public BookCopiesImpl(String book_id, int branch_id, int no_of_copies) {
		super();
		this.book_id = book_id;
		this.branch_id = branch_id;
		this.no_of_copies = no_of_copies;
	}

	/**
	 * 
	 */
	public BookCopiesImpl() {
		super();
	}

}
