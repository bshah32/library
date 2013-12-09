package deepshah.library.controller.impl;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import deepshah.library.jspmodels.BookAuthorRelation;
import deepshah.library.jspmodels.BookLoanBorrowerRelation;
import deepshah.library.model.Book;
import deepshah.library.model.BookLoans;
import deepshah.library.model.Borrower;
import deepshah.library.model.LibraryBranch;
import deepshah.library.model.impl.BookImpl;
import deepshah.library.model.impl.BookLoansImpl;
import deepshah.library.model.impl.LibraryBranchImpl;
import deepshah.library.service.LibrarianService;

@Controller
public class BookController {

	@Autowired
	LibrarianService librarian_service;

	@RequestMapping(value = "/book/bookavailability", method = RequestMethod.GET)
	public ModelAndView bookavailability() {
		ModelAndView mv = new ModelAndView("book/bookAvailability");
		mv.addObject("model", new BookAuthorRelation());
		mv.addObject("status","Enter Details below");
		return mv;
	}
	
	@RequestMapping(value = "/book/searchingBookbyNameIdAuthor", method = RequestMethod.POST)
	public ModelAndView onSearchingBookByNameAndId(@RequestParam String submitbutton,
			@ModelAttribute("book_model") @Valid BookAuthorRelation book_and_author,
			BindingResult result, Model model) throws IllegalStateException,
			IOException,Exception {
		List<Object[]> list = null;
		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			ModelAndView mv = new ModelAndView("book/bookAvailability");
			return mv;
		}
		
		if(book_and_author.getBook().getBook_id() == "" && book_and_author.getBook().getTitle()=="" 
				&& book_and_author.getAuthor().getAuthor_name() == ""){
			ModelAndView mv = new ModelAndView("book/bookAvailability");
			mv.addObject("model", new BookAuthorRelation());
			mv.addObject("status","Please enter either Book Id or Book Title or Book Author Name");
			return mv;
		}
		list = librarian_service.getBookAvailabilityByIdAndName(book_and_author.getBook().getBook_id(),
				book_and_author.getBook().getTitle(), book_and_author.getAuthor().getAuthor_name());
		
		if(submitbutton.equalsIgnoreCase("pdf")){
			ModelAndView mv = new ModelAndView("bookavailabilitylistview");
			mv.getModelMap().addAttribute("custom",list);
			return mv;
		}
		ModelAndView mv = new ModelAndView("/book/bookAvailabilityList");
		mv.addObject("custom", list);
		mv.addObject("status","You have fetched "+list.size()+" result in Responce");
		return mv;
	}
	


	@RequestMapping(value = "/book/bookcheckout", method = RequestMethod.GET)
	public ModelAndView bookcheckin() {
		ModelAndView mv = new ModelAndView("book/checkoutBook");
		mv.addObject("book_loans_model", new BookLoansImpl());
		mv.addObject("status","");
		return mv;
	}
	
	@RequestMapping(value = "/book/onBookCheckout", method = RequestMethod.POST)
	public ModelAndView onBookCheckin(
			@ModelAttribute("book_loans_model") @Valid BookLoansImpl new_book,
			BindingResult result, Model model) throws IllegalStateException,
			IOException,EntityExistsException,EntityNotFoundException{
		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			ModelAndView mv = new ModelAndView("book/checkoutBook");
			mv.addObject("status","Error in checkin book.Please check the information.");
			return mv;
		}
		ModelAndView mv = new ModelAndView("book/checkoutBook");
		mv.addObject("book_loans_model", new BookLoansImpl());
		
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
	
	@RequestMapping(value = "/book/bookcheckin", method = RequestMethod.GET)
	public String bookcheckout() {
		return "/book/checkinBook";
	}
	
	@RequestMapping(value = "/book/searcheckinbooks/*", method = RequestMethod.GET)
	public ModelAndView searchCheckoutBook(HttpServletRequest request) throws Exception  {
		String book_id = request.getParameter("bookid");
		String card_no = request.getParameter("cardno");
		String first_name = request.getParameter("fname");
		String last_name = request.getParameter("lname");
		if(book_id.equals("") && card_no.equals("") && first_name.equals("") && last_name.equals("")){
			ModelAndView mv = new ModelAndView("/book/checkinBook");
			mv.addObject("status","Enter any detail to Search Book");
			return mv;
		}
		if(request.getParameter("submitbutton").equalsIgnoreCase("pdf")){
			List<Object[]> list =librarian_service.getIssuedBook(book_id, card_no, first_name, last_name);
			ModelAndView mv = new ModelAndView("bookcheckinsearchlist");
			mv.addObject("custom", list);
			return mv;
		}
		List<Object[]> list =librarian_service.getIssuedBook(book_id, card_no, first_name, last_name);
		List<BookLoanBorrowerRelation> bookdisp = new ArrayList<BookLoanBorrowerRelation>();
		for (Object result[] : list) {
				BookLoanBorrowerRelation bs = new BookLoanBorrowerRelation();
				bs.setBook_id(String.valueOf(result[0]));
				bs.setBranch_id(String.valueOf(result[1]));
				bs.setCard_no(Integer.valueOf(String.valueOf(result[2])));
				bs.setFname(String.valueOf(result[3]));
				bs.setLname(String.valueOf(result[4]));
				bs.setDate_out(Date.valueOf(String.valueOf(result[5])));
				bs.setDue_date(Date.valueOf(String.valueOf(result[6])));
				bookdisp.add(bs);
		  }
		
		ModelAndView mv = new ModelAndView("/book/checkinBookList");
		mv.addObject("book_loans_model", bookdisp);
		return mv;
	}
	
	@RequestMapping(value = "/book/onSelectedBookCheckin/{book_id}/{branch_id}/{card_no}", method = RequestMethod.GET)
	public String deleteanissuedbook(@PathVariable("book_id") String bookId,@PathVariable("branch_id") int branchId,
			@PathVariable("card_no") String cardNo) throws Exception {
		BookLoansImpl book = new BookLoansImpl(bookId, branchId, cardNo, null,null); 
		librarian_service.checkoutOldBook(book);
		return "redirect:/book/bookcheckin";
	}
	
	@RequestMapping(value = "/book/getallcheckinbooks", method = RequestMethod.GET)
	public ModelAndView onbookcheckout() {
		List<BookLoans> list = librarian_service.getAllIssuedCheckedinBooks();
		ModelAndView mv = new ModelAndView("/book/allBookCheckinList");
		mv.addObject("book_loans_model", list);
		return mv;
	}
	
	@RequestMapping(value = "/book/onBookCheckin/{book_id}/{branch_id}/{card_no}", method = RequestMethod.GET)
	public String deleteissuedbook(@PathVariable("book_id") String bookId,@PathVariable("branch_id") int branchId,
			@PathVariable("card_no") String cardNo) throws Exception {
		BookLoansImpl book = new BookLoansImpl(bookId, branchId, cardNo, null,null); 
		librarian_service.checkoutOldBook(book);
		return "redirect:/book/bookcheckin";
	}
	
	@RequestMapping("book/bookavailabilitylistview/openInPDF")
	public ModelAndView beanToPdf(@ModelAttribute("book_model") @Valid BookAuthorRelation book_and_author,
			BindingResult result, Model model) throws IllegalStateException,
			IOException,Exception{
		List<Object[]> list = null;
		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			ModelAndView mv = new ModelAndView("book/bookAvailability");
			return mv;
		}
		
		if(book_and_author.getBook().getBook_id() == "" && book_and_author.getBook().getTitle()=="" 
				&& book_and_author.getAuthor().getAuthor_name() == ""){
			ModelAndView mv = new ModelAndView("book/bookAvailability");
			mv.addObject("model", new BookAuthorRelation());
			mv.addObject("status","Please enter either Book Id or Book Title or Book Author Name");
			return mv;
		}
		
		list = librarian_service.getBookAvailabilityByIdAndName(book_and_author.getBook().getBook_id(),
				book_and_author.getBook().getTitle(), book_and_author.getAuthor().getAuthor_name());
		ModelAndView mv = new ModelAndView("bookavailabilitylistview");
		mv.getModelMap().addAttribute("custom",list);
		return mv;
	}
	
	@RequestMapping(value = "/book/listallbook")
	public ModelAndView viewAllBooks() {
		ModelAndView mv = new ModelAndView("/book/listAllBook");
		List<Book> list = librarian_service.fetchAllBooks();
		mv.addObject("book_model",list);
		mv.addObject("status","There are total "+String.valueOf(list.size())+" Books in Library");
		return mv;
	}
	
	@RequestMapping("/book/booklistview/openInPDF")
	public ModelAndView beanToPdf() {
		ModelAndView mv = new ModelAndView("booklistview");
		List<Book> list = librarian_service.fetchAllBooks();
		mv.getModelMap().addAttribute("book_model",list);
		return mv;
	}
	
	@RequestMapping(value = "/book/onbookdelete/{book_id}", method = RequestMethod.GET)
	public String deleteBranch(@PathVariable("book_id") String bookId) throws Exception {
		librarian_service.deleteBook(bookId);
		return "redirect:/book/listallbook";
	}
	
	@RequestMapping(value = "/book/onbookupdate/{book_id}", method = RequestMethod.GET)
	public ModelAndView branchupdate(@PathVariable("book_id") String bookId) throws Exception{
		Book book = librarian_service.searchBook(bookId);
		ModelAndView mv = new ModelAndView("/book/updateBook");
		mv.addObject("book_model",book); 
		return mv;
	}
	
	@RequestMapping(value = "/book/onupdatingbook", method = RequestMethod.POST)
	public ModelAndView onUpdatingBook(@ModelAttribute("book_model") @Valid BookImpl book,
			BindingResult result,Model model) {
		System.out.println(book.getBook_id());
		  if(result.hasErrors()){
			 model.addAllAttributes(result.getModel());
			 ModelAndView mv = new ModelAndView("/book/updateBook");
			 mv.addObject("book_model",book);
			 return mv;
	        }
	         
		  ModelAndView mv = new ModelAndView("/book/updateBook");
		  Book oldbook = librarian_service.searchBook(book.getBook_id());
		  if(oldbook.equals(book)){
			  mv.addObject("status","No changes to Update");
		  } 
		  else {
			Book updatedbook = librarian_service.updateBook(book);
			if(updatedbook.equals(book)){
				mv.addObject("status","Book has been updated");	
			}
			else {
				mv.addObject("status","Book has not been updated");
			}
		  }
		return mv;
	}
	
}
