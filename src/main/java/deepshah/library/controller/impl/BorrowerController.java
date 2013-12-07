package deepshah.library.controller.impl;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import deepshah.library.model.Borrower;
import deepshah.library.model.LibraryBranch;
import deepshah.library.model.impl.BorrowerImpl;
import deepshah.library.model.impl.LibraryBranchImpl;
import deepshah.library.service.LibrarianService;

@Controller
public class BorrowerController {

	@Autowired
	LibrarianService librarian_service;
	
	@RequestMapping(value = "/borrower/addborrower", method = RequestMethod.GET)
	public ModelAndView addborrower() {
		ModelAndView mv = new ModelAndView("borrower/addBorrower");
		mv.addObject("output", "Enter Details for Borrower");
		BorrowerImpl bor = new BorrowerImpl();
		int card_no = librarian_service.getMaxCardNo();
		int next_no = card_no+1;
		bor.setCard_no(String.valueOf(next_no));
		mv.addObject("borrower_model", bor);
		mv.addObject("status", "");
		return mv;
	}
	@RequestMapping(value = "/book/onaddingBorrower", method = RequestMethod.POST)
	public ModelAndView onaddingBorrower(
			@ModelAttribute("borrower_model") @Valid BorrowerImpl borrower,
			BindingResult result, Model model) throws IllegalStateException,
			IOException,EntityExistsException {
		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			ModelAndView mv = new ModelAndView("borrower/addBorrower");
			mv.addObject("output", "Enter Details for Borrower");
			mv.addObject("status","Error in checkin book.Please check the information.");
			return mv;
		}
		ModelAndView mv = new ModelAndView("/borrower/addBorrower");
		mv.addObject("output", "Enter Details for Borrower");
		Borrower b = librarian_service.searchBorrower(borrower.getCard_no());
		if(b != null){
			mv.addObject("status","Borrower with same Id already exists");
		}
		else {
		boolean temp = librarian_service.newBorrower(borrower);
		BorrowerImpl bor = new BorrowerImpl();
		int card_no = librarian_service.getMaxCardNo();
		int next_no = card_no+1;
		bor.setCard_no(String.valueOf(next_no));
		mv.addObject("book_loans_model", bor);
		if(temp){
			mv.addObject("status","Borrower has been added");	
		}
		else{
			mv.addObject("status","Borrower has not been added same First Name, Last Name and Address may already exist");
		}
		}
	return mv;
	}
	
	@RequestMapping(value = "/borrower/listBorrower")
	public ModelAndView viewAllBorrower() {
		ModelAndView mv = new ModelAndView("/borrower/listAllBorrower");
		List<Borrower> list = librarian_service.fetchAllBorrower();
		mv.addObject("borrower_model",list);
		mv.addObject("status","There are total "+String.valueOf(list.size())+" Borrower in library");
		return mv;
	}
	@RequestMapping(value = "/borrower/onborrowerdelete/{card_no}", method = RequestMethod.GET)
	public String deleteBranch(@PathVariable("card_no") String card_no) throws Exception {
		librarian_service.deleteBorrower(card_no);
		return "redirect:/borrower/listBorrower";
	}
	
	@RequestMapping(value = "/borrower/editborrower", method = RequestMethod.GET)
	public String updateBranch() {
		return "branch/updateBorrower";
	}
	
	@RequestMapping(value = "/borrower/onborrowerupdate/{card_no}", method = RequestMethod.GET)
	public ModelAndView borrowerUpdate(@PathVariable("card_no") String cardNo) throws Exception{
		System.out.println(cardNo);
		Borrower borrower = librarian_service.searchBorrower(cardNo);
		ModelAndView mv = new ModelAndView("/borrower/updateBorrower");
		mv.addObject("borrower_model",borrower); 
		return mv;
	}
	
	@RequestMapping("/borrower/borrowerlistview/openInPDF")
	public ModelAndView beanToPdf() {
		ModelAndView mv = new ModelAndView("borrowerlistview");
		List<Borrower> list = librarian_service.fetchAllBorrower();
		mv.getModelMap().addAttribute("borrower_model",list);
		return mv;
	}
}
