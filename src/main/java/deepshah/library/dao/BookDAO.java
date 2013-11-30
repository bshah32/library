package deepshah.library.dao;

import deepshah.library.model.Book;

public interface BookDAO {

	void insert(Book book);

	Book update(Book book);

	void remove(Book book);

	Book find(Book book);

	boolean isExists(Book book);

	boolean isExists(String bookId);


}
