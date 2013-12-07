package deepshah.library.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import deepshah.library.service.LibrarianService;

@Controller
public class MainController {

	@Autowired
	LibrarianService librarian_service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "home/home";
	}
	
}
