package data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

public class BookmarkFileDAO implements BookmarkDAO {
	private static final String FILE_NAME="/WEB-INF/states.csv";
	private List<Bookmark> states = new ArrayList<>();
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
				String abbrev = tokens[1];
				String name = tokens[2];
				String capital = tokens[3];
				String latitude = tokens[4];
				String longitude = tokens[5];
				String population = tokens[6];
				String bird = tokens[7];
				states.add(new Bookmark(abbrev, name, capital, latitude, longitude, population, bird));
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	@Override
	public Bookmark getStateByName(String name) {
		Bookmark s = null;
		for (Bookmark state : states) {
			if (state.getName().equalsIgnoreCase(name)) {
				s = state;
				break;
			}
		}
		return s;
	}
	@Override
	public Bookmark getNextState(String name) {
		Bookmark s = null;
		boolean isNext = false;
		if (name.equalsIgnoreCase("Wyoming")){
			return getStateByName("Alabama");
		}
		for (Bookmark state : states) {
			if (isNext == true){
				s = state;
				break;
			}
			if (state.getName().equalsIgnoreCase(name)) {
				isNext = true;
			}
		}
		return s;
	}
	@Override
	public Bookmark getBackState(String name) {
		Bookmark s = null;
		boolean isBack = false;
		if (name.equalsIgnoreCase("Alabama")){
			return getStateByName("Wyoming");
		}
		for (Bookmark state : states) {
			if (state.getName().equalsIgnoreCase(name)) {
				isBack = true;
			}
			if (isBack == true){
				break;
			}
			s = state;
		}
		return s;
	}
	@Override
	public Bookmark getStateByAbbreviation(String abbrev) {
		Bookmark s = null;
		for (Bookmark state : states) {
			if (state.getAbbreviation().equalsIgnoreCase(abbrev)) {
				s = state;
				break;
			}
		}
		return s;
	}
	@Override
	public void addState(Bookmark state) {
		states.add(state);
	}
}

