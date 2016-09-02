package data;

import java.util.Collection;

public interface BookDAO {
	public Book getBookByIsbn(String isbn);
	public void addBook(String isbn, String title, String author);
	public void editBook(String isbn, String title, String author);
	public void removeBook(String isbn);
	public Collection<Book> getBooks();
}
