package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.BookDAO;

@Controller
public class BookController {
	@Autowired
	private BookDAO bookDao;
	
	@RequestMapping(path="GetBookData.do", 
			method=RequestMethod.GET)
	public ModelAndView getAllBooks() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("allBooks.jsp");
		mv.addObject("books", bookDao.getBooks());
		return mv;
	}
	
	@RequestMapping(path="AddBookData.do", 
			method=RequestMethod.POST)
	public ModelAndView addBookInfo(@RequestParam("isbn") String isbn,@RequestParam("title") String title, @RequestParam("author") String author) {
		//Add book
		bookDao.addBook(isbn, title, author);
		
		//Send new book to display all page
		ModelAndView mv = new ModelAndView();
		mv.setViewName("allBooks.jsp");
		mv.addObject("books", bookDao.getBooks());
		return mv;
	}
	
	//Go to edit page
	@RequestMapping(path="EditBookData.do", 
			method=RequestMethod.GET)
	public ModelAndView editBookInfo(@RequestParam("isbn") String isbn) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("editBook.jsp");
		mv.addObject("book", bookDao.getBookByIsbn(isbn));
		return mv;
	}
	
	//Actually edit book
	@RequestMapping(path="EditBookData.do", 
			method=RequestMethod.POST)
	public ModelAndView editBookInfo(@RequestParam("title") String title, @RequestParam("author") String author, @RequestParam("isbn") String isbn) {
		//Change values
		bookDao.editBook(isbn, title, author);
		
		//Send edited book to display all page
		ModelAndView mv = new ModelAndView();
		mv.setViewName("allBooks.jsp");
		mv.addObject("books", bookDao.getBooks());
		return mv;
	}
	
	@RequestMapping(path="DeleteBookData.do", 
			method=RequestMethod.POST)
	public ModelAndView deleteBookInfo(@RequestParam("isbn") String isbn) {
		//Change values
		bookDao.removeBook(isbn);
		
		//Go to display all page minus this book
		ModelAndView mv = new ModelAndView();
		mv.setViewName("allBooks.jsp");
		mv.addObject("books", bookDao.getBooks());
		return mv;
	}

}
