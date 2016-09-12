package data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookDbDAO implements BookDAO {
	private static String url = "jdbc:mysql://localhost:3306/bookshelf";
	private static String user = "student";
	private static String pword = "student";

	public BookDbDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace(System.err);
		}
	}

	@Override
	public Book getBookByIsbn(String isbn) {
		Book book = null;

		try {
			Connection conn = DriverManager.getConnection(url, user, pword);

			String sqltxt;
			sqltxt = "SELECT b.isbn, b.title, a.author_name FROM books b JOIN book_author ba ON b.isbn = ba.isbn JOIN author a ON a.id = ba.author_id WHERE b.isbn = ?";
			PreparedStatement stmt = conn.prepareStatement(sqltxt);
			stmt.setString(1, isbn);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				System.out.println("got a book");
				book = new Book(rs.getString(1), rs.getString(2), rs.getString(3));
			}
			try {
				sqltxt = "UPDATE books SET cover_img_url = ? WHERE isbn = ?";
				String coverImage = new CoverImage().getCoverImage(isbn);
				if (coverImage.equals("")) {
					coverImage = "https://books.google.com/googlebooks/images/no_cover_thumb.gif";
				}
				stmt = conn.prepareStatement(sqltxt);
				stmt.setString(1, coverImage);
				stmt.setString(2, isbn);
				int uc = stmt.executeUpdate();
			} catch (IOException e) {
				System.out.println("IO exception while fetching image url.");
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace(System.err);
		}
		System.out.println("Get by isbn: " + book);
		return book;
	}

	@Override
	public void addBook(String isbn, String title, String author) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pword);

			String sqltxt;

			// Books table
			sqltxt = "INSERT INTO books (isbn, title, cover_img_url) VALUES (?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sqltxt);
			stmt.setString(1, isbn);
			stmt.setString(2, title);
			String coverImage = null;
			try {
				coverImage = new CoverImage().getCoverImage(isbn);
				if (coverImage.equals("")) {
					coverImage = "https://books.google.com/googlebooks/images/no_cover_thumb.gif";
				}
			} catch (IOException e) {
				coverImage = "https://books.google.com/googlebooks/images/no_cover_thumb.gif";
			}
			stmt.setString(3, coverImage);
			int bookExecute = stmt.executeUpdate();
			if (bookExecute == 1) {
				System.out.println("Book added");
			} else {
				System.err.println("No book added");
			}

			// Author table
			sqltxt = "INSERT INTO author (author_name) VALUES (?)";
			stmt = conn.prepareStatement(sqltxt, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, author);
			int authorExecute = stmt.executeUpdate();
			if (authorExecute == 1) {
				System.out.println("Author added");
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int authid = keys.getInt(1);
					System.out.println("New author id: " + authid);
					// book_author table
					sqltxt = "INSERT INTO book_author (isbn, author_id) VALUES (?, ?)";
					stmt = conn.prepareStatement(sqltxt);
					stmt.setString(1, isbn);
					stmt.setInt(2, authid);
					int bookAuthExecute = stmt.executeUpdate();
					if (bookAuthExecute == 1) {
						System.out.println("Author link added");
					} else {
						System.out.println("Author link not added");
					}
				}
			} else {
				System.err.println("No author added");
			}

			stmt.close();
			conn.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace(System.err);
		}

	}

	@Override
	public void editBook(String isbn, String title, String author) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pword);

			String sqltxt;
			sqltxt = "UPDATE books b JOIN book_author ba ON b.isbn = ba.isbn JOIN author a ON ba.author_id = a.id SET b.title = ?, a.author_name = ? WHERE b.isbn = ?";
			PreparedStatement stmt = conn.prepareStatement(sqltxt);
			stmt.setString(1, title);
			stmt.setString(2, author);
			stmt.setString(3, isbn);
			int uc = stmt.executeUpdate();

			if (uc == 1) {
				System.out.println("Edited book: " + isbn);
			} else {
				System.out.println(isbn + ": not edited");
			}

			stmt.close();
			conn.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace(System.err);
		}

	}

	@Override
	public void removeBook(String isbn) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pword);
			String sqltxt = "DELETE FROM book_author WHERE isbn = ?";
			PreparedStatement stmt = conn.prepareStatement(sqltxt);
			stmt.setString(1, isbn);
			int uc = stmt.executeUpdate();
			if (uc > 0) {
				System.out.println(isbn + ": deleted from book_author");
			}
			sqltxt = "DELETE FROM books WHERE isbn = ?";
			stmt = conn.prepareStatement(sqltxt);
			stmt.setString(1, isbn);
			uc = stmt.executeUpdate();
			if (uc > 0) {
				System.out.println(isbn + ": deleted from book");
			}

			stmt.close();
			conn.close();

		} catch (SQLException sqle) {
			System.err.println(sqle);
			sqle.printStackTrace(System.err);
		}

	}

	@Override
	public Collection<Book> getBooks() {
		List<Book> books = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(url, user, pword);
			String sql = "SELECT * from books";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Book book = new Book();

				book.setIsbn(rs.getString(1));
				book.setTitle(rs.getString(2));
				book.setCoverImage(rs.getString(3));

				books.add(book);
			}
			System.out.println(books);
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException sqle) {
			System.err.println(sqle);
			sqle.printStackTrace(System.err);
		}
		return books;

	}

}
