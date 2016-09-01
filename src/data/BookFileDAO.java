package data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

public class BookFileDAO implements BookDAO {
	private static final String FILE_NAME="/WEB-INF/books.csv";
	private Map<String, Book> books = new HashMap<>();
	/*
	 * Use Autowired to have Spring inject an instance
	 * of a WebApplicationContext into this object after
	 * creation.  We will use the WebApplicationContext to
	 * retrieve an ServletContext so we can read from a 
	 * file.
	 */
	@Autowired 
	private WebApplicationContext wac;

	/*
	 * The @PostConstruct method is called by Spring after 
	 * object creation and dependency injection
	 */
	@PostConstruct
	public void init() {
		// Retrieve an input stream from the servlet context
		// rather than directly from the file system
		try (
				InputStream is = wac.getServletContext().getResourceAsStream(FILE_NAME);
				BufferedReader buf = new BufferedReader(new InputStreamReader(is));
			) {
			String line = buf.readLine();
			while ((line = buf.readLine()) != null) {
				String[] tokens = line.split(",");
				String isbn = tokens[0];
				String title = tokens[1];
				String author = tokens[2];
				books.put(isbn, new Book(isbn, title, author));
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	@Override
	public Book getBookByIsbn(String isbn) {
		return books.get(isbn);
	}
	
	@Override
	public void addBook(Book bookmark) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Book> getBooks() {
		return books.values();
		
	}


}

