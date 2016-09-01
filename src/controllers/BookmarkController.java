package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import data.Bookmark;
import data.BookmarkDAO;

@Controller
@SessionAttributes("currentBook")
public class BookmarkController {
	@Autowired
	private BookmarkDAO bookmarkDao;

	@ModelAttribute("currentBook")
	public String initState() {
		return "";
	}
	
	@RequestMapping(path="GetBookData.do", 
			params="isbn",
			method=RequestMethod.GET)
	public ModelAndView getByName(@RequestParam("isbn") String n) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result.jsp");
		mv.addObject("book", bookmarkDao.getBookByIsbn(n));
		mv.addObject("currentBook", bookmarkDao.getBookByIsbn(n).getName());
		return mv;
	}
//	@RequestMapping(path="GetBookData.do", 
//			params="next",
//			method=RequestMethod.GET)
//	public ModelAndView getNextByState(@ModelAttribute("currentState") String state) {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("result.jsp");
//		mv.addObject("state", stateDao.getNextState(state));
//		mv.addObject("currentState", stateDao.getNextState(state).getName());
//		return mv;
//	}
//	@RequestMapping(path="GetBookData.do", 
//			params="back",
//			method=RequestMethod.GET)
//	public ModelAndView getBackByState(@ModelAttribute("currentState") String state) {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("result.jsp");
//		mv.addObject("state", stateDao.getBackState(state));
//		mv.addObject("currentState", stateDao.getBackState(state).getName());
//		return mv;
//	}
//	
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
//	public ModelAndView newState(Bookmark state) {
//		stateDao.addState(state);
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("result.jsp");
//		return mv;
//	}
}
