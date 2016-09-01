package data;

import java.io.IOException;

public class Book {
	private String isbn;
	private String title;
	private String author;
	private String coverImage;
	
	public Book() {
	}
	
	public Book(String isbn, String title, String author) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		try {
			this.coverImage = new CoverImage().getCoverImage(isbn);
			if (coverImage.equals("")){
				coverImage = "https://books.google.com/googlebooks/images/no_cover_thumb.gif";
			}
		} catch (IOException e) {
			this.coverImage = "https://books.google.com/books/content?id=NOTFOUND&printsec=frontcover&img=1&zoom=1&source=gbs_api";
		}
	}
	
	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", coverImage=" + coverImage + "]";
	}

}
