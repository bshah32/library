package deepshah.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import deepshah.library.dao.LibraryBranchDAO;
import deepshah.library.model.LibraryBranch;

@Controller
public class MainController {

	@Autowired
	LibraryBranchDAO libraria_dao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "admin/adminHome";
	}

	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	public ModelAndView list() throws Exception {
		List list = libraria_dao.getAllBranches();
		ModelAndView mv = new ModelAndView("/admin/adminList");
		mv.addObject("output", "All Branches");
		mv.addObject("librarianList", list);
		return mv;
	}

}
