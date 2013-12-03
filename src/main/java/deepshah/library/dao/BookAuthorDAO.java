package deepshah.library.dao;

import deepshah.library.model.BookAuthors;

public interface BookAuthorDAO {

	void insert(BookAuthors author);

	BookAuthors update(BookAuthors author);

	void remove(BookAuthors author);

	BookAuthorDAO find(String bookId, String authorName);

	boolean isExists(BookAuthors author);

	boolean isExists(String bookId, String authorName);


}
