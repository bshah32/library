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
import javax.validation.Valid;

import deepshah.library.model.BookAuthors;

@Entity
@Table(name = "book_authors")
public class BookAuthorsImpl implements BookAuthors, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "book_id", nullable = false,columnDefinition="varchar(15) default ''")
	private String book_id;

	@Id
	@Column(name = "author_name",length = 30,columnDefinition="varchar(30) default ''")
	private String author_name;

	
	@ManyToOne(targetEntity = BookImpl.class, fetch = FetchType.LAZY,cascade={CascadeType.ALL})
	@JoinColumn(name = "book_id", referencedColumnName = "book_id")
	private BookImpl book;

	@Override
	public String getAuthor_name() {
		return author_name;
	}
	
	@Override
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	@Override
	public String getBook_id() {
		return book_id;
	}
	
	@Override 
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((author_name == null) ? 0 : author_name.hashCode());
		result = prime * result + ((book_id == null) ? 0 : book_id.hashCode());
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
		BookAuthorsImpl other = (BookAuthorsImpl) obj;
		if (author_name == null) {
			if (other.author_name != null)
				return false;
		} else if (!author_name.equals(other.author_name))
			return false;
		if (book_id == null) {
			if (other.book_id != null)
				return false;
		} else if (!book_id.equals(other.book_id))
			return false;
		return true;
	}

	/**
	 * 
	 */
	public BookAuthorsImpl() {
		super();
	}

	/**
	 * @param book_id
	 * @param author_name
	 */
	public BookAuthorsImpl(String book_id, String author_name) {
		super();
		this.book_id = book_id;
		this.author_name = author_name;
	}

	public BookImpl getBook() {
		return book;
	}

	public void setBook(BookImpl book) {
		this.book = book;
	}

	
}
