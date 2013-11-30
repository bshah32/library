package deepshah.library.dao;

import java.util.List;

public interface CustomDAO {

	List<Object[]> getByTitle(String title);

	List<Object[]> getById(String book_id);

	List<Object[]> getByAuthor(String book_author);

	List<Object[]> getByIdAndTitle(String book_id, String title);

	int getBookAvailabilityInBranch(String ofBook, String inBranch);

}
