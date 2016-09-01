package data;

import java.util.Collection;

public interface BookDAO {
	public Book getBookByIsbn(String isbn);
	public void addBook(Book bookmark);
	public Collection<Book> getBooks();
}
