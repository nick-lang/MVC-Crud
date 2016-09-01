package data;

public class Bookmark {
	private String isbn;
	private String name;
	private int page;
	private String quote;

	public Bookmark() {
	}

	public Bookmark(String isbn, String name, int page, String quote) {
		super();
		this.isbn = isbn;
		this.name = name;
		this.page = page;
		this.quote = quote;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	@Override
	public String toString() {
		return "Bookmark [isbn=" + isbn + ", name=" + name + ", page=" + page + ", quote=" + quote + "]";
	}
	
}
