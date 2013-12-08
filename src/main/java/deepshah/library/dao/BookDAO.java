package deepshah.library.dao;

import java.sql.SQLException;
import java.util.List;

import deepshah.library.model.Book;

public interface BookDAO {

	void insert(Book book);

	Book update(Book book);

	void remove(Book book);

	Book find(Book book);

	boolean isExists(Book book);

	boolean isExists(String bookId);

	List<Book> getAllBooks();

	void remove(String bookId);

	Book findBook(String bookId);


}
