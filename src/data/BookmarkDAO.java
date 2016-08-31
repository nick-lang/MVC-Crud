package data;

public interface BookmarkDAO {
	public Bookmark getStateByName(String name);
	public Bookmark getStateByAbbreviation(String abbreviation);
	public Bookmark getNextState(String name);
	public void addState(Bookmark state);
	public Bookmark getBackState(String name);
}
