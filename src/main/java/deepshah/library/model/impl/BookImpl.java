package deepshah.library.model.impl;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import deepshah.library.model.Book;

@Entity
@Table(name = "book")
@NamedQueries({ @NamedQuery(name = "fetchAllBooksQuery", query = "SELECT b FROM BookImpl b")
// ,
// @NamedQuery(name="deleteBookByIdQuery",
// query="DELETE b FROM JPABookImpl b WHERE b.bookId =:bookId")
})

public class BookImpl implements Book {

	@Id
	@Column(name = "book_id", nullable = false,columnDefinition="varchar(15) default ''")
	private String book_id;

	@Column(name = "title", length = 100)
	private String title;

	@OneToMany(mappedBy = "book_id", targetEntity = BookAuthorsImpl.class,cascade={CascadeType.ALL})
	Set<BookAuthorsImpl> authors_set;

	@OneToMany(mappedBy = "book_id", targetEntity = BookCopiesImpl.class,cascade={CascadeType.ALL})
	Set<BookCopiesImpl> book_copies_set;

	@OneToMany(mappedBy = "book_id", targetEntity = BookLoansImpl.class,cascade={CascadeType.ALL})
	Set<BookLoansImpl> bookloans_set;

	@Override
	public String getBook_id() {
		return book_id;
	}

	@Override
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book_id == null) ? 0 : book_id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		BookImpl other = (BookImpl) obj;
		if (book_id == null) {
			if (other.book_id != null)
				return false;
		} else if (!book_id.equals(other.book_id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * @param book_id
	 * @param title
	 */
	public BookImpl(String book_id, String title) {
		super();
		this.book_id = book_id;
		this.title = title;
	}

	/**
	 * 
	 */
	public BookImpl() {
	}

}
