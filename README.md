#Library
This project was created by Nick Lang.

Library is a basic Spring-MVC Crud app that stores book objects. Library uses HTML and CSS for presentation. A JSON is parsed to dynamically fetch cover images.

This project was updated to persist data using mysql.

This project can be found functioning at http://www.nicklang.io:8080/Bookmark/

##Future Features
-Ability for users to add references to quotes and ideas contained in a book.

-Ability to list references that a book makes to other books and authors.

-Essentially, I would like to turn Library into a functional idea index. If I had an idea index before this, I would know where I got this idea.

###Bug Fixes
-Authors are never deleted when deleting a book. Although they could be deleted, the plan is to make it so that existing authors can be referenced when adding a new book.

##Stumbling Points
-CSS

-CoverImage.java takes an ISBN and pulls a cover image associated with that ISBN from the web. It does this by parsing a Google Books JSON and finding a URL that links to the cover image. The trickiest part of this was that initially I was unable to read the entire JSON. This was solved by reading in the JSON as a buffered byte array.

##Special Thanks:
Rod Hammond: Outlining the functionality of a program that reads and parses a JSON.

Rob Roselius: Showed how to read in the JSON as a buffered byte array.
