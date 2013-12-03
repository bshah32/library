package deepshah.library.controller.impl;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import deepshah.library.dao.LibraryBranchDAO;
import deepshah.library.model.BookLoans;
import deepshah.library.model.Borrower;
import deepshah.library.model.impl.BookAuthorsImpl;
import deepshah.library.model.impl.BookImpl;
import deepshah.library.model.impl.BookLoansImpl;
import deepshah.library.model.impl.BorrowerImpl;
import deepshah.library.service.LibrarianService;

@Controller
public class MainController {

	@Autowired
	LibrarianService librarian_service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "home/home";
	}
	
	@RequestMapping(value = "/book/bookavailability", method = RequestMethod.GET)
	public ModelAndView bookavailability() {
		ModelAndView mv = new ModelAndView("book/bookAvailability");
		mv.addObject("output", "Search Book");
		mv.addObject("book_model", new BookImpl());
		mv.addObject("author_model", new BookAuthorsImpl());
		return mv;
	}
	
	@RequestMapping(value = "/book/searchingBookbyNameandId", method = RequestMethod.POST)
	public ModelAndView onSearchingBookByNameAndId(
			@ModelAttribute("book_model") @Valid BookImpl book,
			@ModelAttribute("author_model") @Valid BookAuthorsImpl author,
			BindingResult result, Model model) throws IllegalStateException,
			IOException {
		List<Object[]> list = null;
		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			ModelAndView mv = new ModelAndView("book/bookAvailability");
			return mv;
		}
		System.out.println("Id :"+book.getBook_id());
		System.out.println("Title :" +book.getTitle());
		
		if(book.getTitle() == "" && book.getBook_id()==""){
			ModelAndView mv = new ModelAndView("book/bookAvailability");
			return mv;	
		}
		else if((book.getBook_id() != "") && (book.getTitle() == "")){
				list = librarian_service.getBookAvailabilityById(book.getBook_id());		
			}
		else if((book.getBook_id() == "") && (book.getTitle() != "")){
			list = librarian_service.getBookAvailabilityByName(book.getTitle());		
		}
		else {
			list = librarian_service.getBookAvailabilityByIdAndName(book.getBook_id(), book.getTitle());
		}

		System.out.println("List size is : " + list.size());
		ModelAndView mv = new ModelAndView("/book/bookAvailabilityList");
		mv.addObject("output", "Listing Available Books");
		mv.addObject("custom", list);
		mv.addObject("status","You have fetched "+list.size()+" result in Responce");
		return mv;
	}
	
	@RequestMapping(value = "/book/searchingBookByAuthor", method = RequestMethod.POST)
	public ModelAndView onSearchingBookByAuthor(
			@ModelAttribute("author") @Valid BookAuthorsImpl author,
			BindingResult result, Model model) throws IllegalStateException,
			IOException {
		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			ModelAndView mv = new ModelAndView("book/bookAvailabilityList");
			return mv;
		}
		List<Object[]> list = librarian_service.getBookAvailabilityByAuthor(author.getAuthor_name());
		ModelAndView mv = new ModelAndView("/book/bookAvailabilityList");
		mv.addObject("output", "Listing Available Books");
		mv.addObject("custom", list);
		return mv;
	}

	@RequestMapping(value = "/book/bookcheckin", method = RequestMethod.GET)
	public ModelAndView bookcheckin() {
		ModelAndView mv = new ModelAndView("book/bookCheckin");
		mv.addObject("output", "Check-in New Book");
		mv.addObject("book_loans_model", new BookLoansImpl());
		mv.addObject("status","");
		return mv;
	}
	
	@RequestMapping(value = "/book/onBookCheckin", method = RequestMethod.POST)
	public ModelAndView onBookCheckin(
			@ModelAttribute("book_loans_model") @Valid BookLoansImpl new_book,
			BindingResult result, Model model) throws IllegalStateException,
			IOException,EntityExistsException,EntityNotFoundException{
		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			ModelAndView mv = new ModelAndView("book/bookCheckin");
			mv.addObject("output", "Enter Checking Details");
			mv.addObject("status","Error in checkin book.Please check the information.");
			return mv;
		}
		ModelAndView mv = new ModelAndView("book/bookCheckin");
		mv.addObject("book_loans_model", new BookLoansImpl());
		mv.addObject("output", "Enter Checking Details");
		
		if(!librarian_service.bookExists(new_book.getBook_id())){
			mv.addObject("status","Given Book Does not Exist");
			return mv;
		}
		if(!librarian_service.branchExists(new_book.getBranch_id())){
			mv.addObject("status","Given Branch Does not Exist");
			return mv;
		}
		if(!librarian_service.borrowerExists(new_book.getCard_no())){
			mv.addObject("status","Given Borrower Does not Exist");
			return mv;
		} else{
			Borrower borrower = librarian_service.findBorrower(new_book.getCard_no());	
			if(librarian_service.validIssueStatusOfBorrower(borrower) >= 3){
				mv.addObject("status","Book Issued Limit Reached for '"+borrower.getFname()+". "+borrower.getLname()+"' .Cannot Issue More than 3 Books");
			return mv;	
			}
		}
		if(!librarian_service.cheackBookAvailablility(new_book.getBook_id(),String.valueOf(new_book.getBranch_id()))){
			mv.addObject("status","Book is not available. All books are issued");
			return mv;
		}
		if(librarian_service.checkinNewBook(new_book)){
			mv.addObject("status","Book has been checked in");
			}
			else {
				mv.addObject("status","Book has not been checked in as it already exists");
			}
			return mv;
	}
	
	@RequestMapping(value = "/book/bookcheckout", method = RequestMethod.GET)
	public ModelAndView bookcheckout() {
		List<BookLoans> list = librarian_service.getAllIssuedCheckedinBooks();
		ModelAndView mv = new ModelAndView("/book/bookCheckout");
		mv.addObject("output", "All Checkied In Books");
		mv.addObject("book_loans_model", list);
		return mv;
	}
	
	@RequestMapping(value = "/book/onBookCheckout/{book_id}/{branch_id}/{card_no}", method = RequestMethod.GET)
	public String deleteLibrarian(@PathVariable("book_id") String bookId,@PathVariable("branch_id") int branchId,
			@PathVariable("card_no") String cardNo) throws Exception {
		BookLoansImpl book = new BookLoansImpl(bookId, branchId, cardNo, null,null); 
		librarian_service.checkoutOldBook(book);
		return "redirect:/book/bookcheckout";
	}
	
	@RequestMapping(value = "/borrower/addborrower", method = RequestMethod.GET)
	public ModelAndView addborrower() {
		ModelAndView mv = new ModelAndView("borrower/addBorrower");
		mv.addObject("output", "Enter Details for Borrower");
		mv.addObject("borrower_model", new BorrowerImpl());
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
		mv.addObject("book_loans_model", new BorrowerImpl());
		if(temp){
			mv.addObject("status","Borrower has been added");	
		}
		else{
			mv.addObject("status","Borrower has not been added same First Name, Last Name and Address may already exist");
		}
		}
	return mv;
	}
}
