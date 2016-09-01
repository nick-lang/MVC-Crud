package data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

public class BookmarkFileDAO implements BookmarkDAO {
	private static final String FILE_NAME="/WEB-INF/bookmarks.csv";
	private Map<String, Bookmark> bookmarks = new HashMap<>();
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
				String name = tokens[1];
				int page = Integer.parseInt(tokens[2]);
				String quote = tokens[3];
				bookmarks.put(isbn, new Bookmark(isbn, name, page, quote));
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	@Override
	public Bookmark getBookByIsbn(String isbn) {
		return bookmarks.get(isbn);
	}
	
	@Override
	public void addBook(Bookmark bookmark) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Bookmark getNextBook(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bookmark getBackBook(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}

