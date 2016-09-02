package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import data.BookDAO;

@Controller
@SessionAttributes("currentBook")
public class BookController {
	@Autowired
	private BookDAO bookDao;

	@ModelAttribute("currentBook")
	public String initBook() {
		return "";
	}
	
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
	@RequestMapping(path="EditBookData.do", 
			method=RequestMethod.GET)
	public ModelAndView editBookInfo(@RequestParam("isbn") String isbn) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("editBook.jsp");
		System.out.println("in editBookInfo");
		mv.addObject("book", bookDao.getBookByIsbn(isbn));
		return mv;
	}
	
//	@RequestMapping(path="GetBookData.do", 
//			params="abbr",
//			method=RequestMethod.GET)
//	public ModelAndView getByAbbrev(@RequestParam("abbr") String a) {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("result.jsp");
//		mv.addObject("state", stateDao.getStateByAbbreviation(a));
//		mv.addObject("currentState", stateDao.getStateByAbbreviation(a).getName());
//		return mv;
//	}
//
//	@RequestMapping(path="NewBookmark.do",
//			method=RequestMethod.POST)
//	public ModelAndView newBook(Bookmark state) {
//		stateDao.addState(state);
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("result.jsp");
//		return mv;
//	}
}
