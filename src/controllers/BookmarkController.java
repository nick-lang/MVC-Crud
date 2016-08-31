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
@SessionAttributes("currentState")
public class BookmarkController {
	@Autowired
	private BookmarkDAO stateDao;

	@ModelAttribute("currentState")
	public String initState() {
		return "";
	}
	
	@RequestMapping(path="GetStateData.do", 
			params="name",
			method=RequestMethod.GET)
	public ModelAndView getByName(@RequestParam("name") String n) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result2.jsp");
		mv.addObject("state", stateDao.getStateByName(n));
		mv.addObject("currentState", stateDao.getStateByName(n).getName());
		return mv;
	}
	@RequestMapping(path="GetStateData.do", 
			params="next",
			method=RequestMethod.GET)
	public ModelAndView getNextByState(@ModelAttribute("currentState") String state) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result2.jsp");
		mv.addObject("state", stateDao.getNextState(state));
		mv.addObject("currentState", stateDao.getNextState(state).getName());
		return mv;
	}
	@RequestMapping(path="GetStateData.do", 
			params="back",
			method=RequestMethod.GET)
	public ModelAndView getBackByState(@ModelAttribute("currentState") String state) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result2.jsp");
		mv.addObject("state", stateDao.getBackState(state));
		mv.addObject("currentState", stateDao.getBackState(state).getName());
		return mv;
	}
	
	@RequestMapping(path="GetStateData.do", 
			params="abbr",
			method=RequestMethod.GET)
	public ModelAndView getByAbbrev(@RequestParam("abbr") String a) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result2.jsp");
		mv.addObject("state", stateDao.getStateByAbbreviation(a));
		mv.addObject("currentState", stateDao.getStateByAbbreviation(a).getName());
		return mv;
	}

	@RequestMapping(path="NewState.do",
			method=RequestMethod.POST)
	public ModelAndView newState(Bookmark state) {
		stateDao.addState(state);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result2.jsp");
		return mv;
	}
}
