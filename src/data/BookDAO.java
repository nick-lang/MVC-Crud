package data;

import java.util.Collection;

public interface BookDAO {
	public Book getBookByIsbn(String isbn);
	public void addBook(String isbn, String title, String author);
	public void editBook(Book bk);
	public void removeBook(Book bk);
	public Collection<Book> getBooks();
}
