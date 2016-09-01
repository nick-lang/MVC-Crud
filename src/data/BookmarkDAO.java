package data;

public interface BookmarkDAO {
	public Bookmark getBookByIsbn(String isbn);
	public Bookmark getNextBook(String name);
	public void addBook(Bookmark bookmark);
	public Bookmark getBackBook(String name);
}
