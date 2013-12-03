package deepshah.library.jspmodels;

import javax.validation.Valid;

import deepshah.library.model.impl.BookAuthorsImpl;
import deepshah.library.model.impl.BookImpl;

public class BookAuthorRelation {

	@Valid
	private BookImpl book;
	
	@Valid
	private BookAuthorsImpl author;

	public BookImpl getBook() {
		return book;
	}

	public void setBook(BookImpl book) {
		this.book = book;
	}

	public BookAuthorsImpl getAuthor() {
		return author;
	}

	public void setAuthor(BookAuthorsImpl author) {
		this.author = author;
	}
	
	
}
