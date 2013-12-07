package deepshah.library.controller.impl;

import java.util.ArrayList;
import java.util.List;

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
import deepshah.library.model.impl.BookLoansImpl;
import deepshah.library.model.impl.BorrowerImpl;
import deepshah.library.model.impl.LibraryBranchImpl;
import deepshah.library.service.LibrarianService;


@Controller
public class BranchController {

	
	@Autowired
	LibrarianService librarian_service;
	
	@RequestMapping(value = "/branch/insertbranch", method = RequestMethod.GET)
	public String insertBranch(Model model) {
		LibraryBranchImpl branch = new LibraryBranchImpl();
		int branch_no = librarian_service.getMaxBranchNo();
		int next_no = branch_no+1;
		branch.setBranch_id(next_no);
		model.addAttribute("library_branch_model",branch);
		        return "branch/insertBranch";
	}
	
	@RequestMapping(value = "/branch/onaddingbranch", method = RequestMethod.POST)
	public ModelAndView onSubmittingBranch(@ModelAttribute("library_branch_model") @Valid LibraryBranchImpl branch,
			BindingResult result,Model model) {
		  if(result.hasErrors()){
			 model.addAllAttributes(result.getModel());
			 ModelAndView mv = new ModelAndView("/branch/insertBranch");
			 return mv;
	        }
	         
		  System.out.println("Branch values is : " + branch.getBranch_id()+":"+branch.getBranch_name()+":"+branch.getAddress());
	      ModelAndView mv = new ModelAndView("/branch/insertBranch");
	      if(librarian_service.searchBranchByName(branch.getBranch_name())){
				mv.addObject("status","Branch has with same name already Exists");
			}
			else {
			boolean temp = librarian_service.insertBranch(branch);
			LibraryBranchImpl newbranch = new LibraryBranchImpl();
			int branch_no = librarian_service.getMaxBranchNo();
			int next_no = branch_no+1;
			branch.setBranch_id(next_no);
			mv.addObject("book_loans_model",newbranch);
			if(temp){
				mv.addObject("status","Branch has been added");	
			}
			else{
				mv.addObject("status","Branch has not been added same Same Branch Name  may already exist");
			}
			}
		return mv;
	}
	
	
	@RequestMapping(value = "/branch/listallbranch")
	public ModelAndView viewAllBranches() {
		ModelAndView mv = new ModelAndView("/branch/listAllBranch");
		List<LibraryBranch> list = librarian_service.fetchAllBranches();
		mv.addObject("library_branch_model",list);
		mv.addObject("status","There are total "+String.valueOf(list.size())+" Departments in Library");
		return mv;
	}
	@RequestMapping(value = "/branch/onbranchdelete/{branch_id}", method = RequestMethod.GET)
	public String deleteBranch(@PathVariable("branch_id") int branchId) throws Exception {
		librarian_service.deleteBranch(branchId);
		return "redirect:/branch/listallbranch";
	}
	
	@RequestMapping(value = "/branch/editbranch", method = RequestMethod.GET)
	public String updateBranch() {
		return "branch/updateBranch";
	}
	
	@RequestMapping(value = "/branch/onbranchupdate/{branch_id}", method = RequestMethod.GET)
	public ModelAndView branchupdate(@PathVariable("branch_id") int branchId) throws Exception{
		System.out.println(branchId);
		LibraryBranch branch = librarian_service.searchBranch(branchId);
		ModelAndView mv = new ModelAndView("/branch/updateBranch");
		mv.addObject("library_branch_model",branch); 
		return mv;
	}
	
	@RequestMapping(value = "/branch/onupdatingbranch", method = RequestMethod.POST)
	public ModelAndView onUpdatingBranch(@ModelAttribute("library_branch_model") @Valid LibraryBranchImpl branch,
			BindingResult result,Model model) {
		  if(result.hasErrors()){
			 model.addAllAttributes(result.getModel());
			 ModelAndView mv = new ModelAndView("/branch/updateBranch");
			 mv.addObject("library_branch_model",branch);
			 return mv;
	        }
	         
		  ModelAndView mv = new ModelAndView("/branch/updateBranch");
		  LibraryBranch oldbranch = librarian_service.searchBranch(branch.getBranch_id());
		  if(oldbranch.equals(branch)){
			  mv.addObject("status","No changes to Update");
		  } 
		  else {
			LibraryBranch updatedbranch = librarian_service.updateBranch(branch);
			if(updatedbranch.equals(branch)){
				mv.addObject("status","Branch has been updated");	
			}
			else{
				mv.addObject("status","Branch has not been updated");
			}
		  }
		return mv;
	}
	
	@RequestMapping("/branch/branchlistview/openInPDF")
	public ModelAndView beanToPdf() {
		ModelAndView mv = new ModelAndView("branchlistview");
		List<LibraryBranch> list = librarian_service.fetchAllBranches();
		mv.getModelMap().addAttribute("library_branch_model",list);
		return mv;
	}
	
}
