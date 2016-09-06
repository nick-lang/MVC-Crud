#Library
This project was created by Nick Lang.
Library is a basic Spring-MVC Crud app that uses html and css for presentation.
This project can be found at functioning at http://www.nicklang.io:8080/Bookmark/

##Future Features
-Ability for users to add references to quotes and ideas contained in a book.
-Ability to list references that a book makes to other books and authors.
-Essentially, I would like to turn Library into a funtional idea index. If I had an idea index before this, I would know where I got this idea.

##Stumbling Points
-CSS
-CoverImage.java takes an ISBN and pulls a cover image associated with that ISBN from the web. It does this by parsing a Google Books JSON and finding a url that links to the cover image. The trickiest part of this was that initially I was unable to read the entire JSON. This was solved by reading in the JSON as a buffered byte array.

##Special Thanks:
Rod Hammond: Outlining the functionality of a program that reads and parses a JSON.
Rob Roselius: Showed how to read in the JSON as a buffered byte array.
