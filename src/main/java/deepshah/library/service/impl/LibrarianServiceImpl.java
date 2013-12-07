package deepshah.library.service.impl;

import java.sql.SQLException;
import java.util.List;

import javassist.bytecode.stackmap.Liveness;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import deepshah.library.dao.BookDAO;
import deepshah.library.dao.BookLoansDAO;
import deepshah.library.dao.BorrowerDAO;
import deepshah.library.dao.CustomDAO;
import deepshah.library.dao.LibraryBranchDAO;
import deepshah.library.jspmodels.BookLoanBorrowerRelation;
import deepshah.library.model.BookLoans;
import deepshah.library.model.Borrower;
import deepshah.library.model.LibraryBranch;
import deepshah.library.model.impl.LibraryBranchImpl;
import deepshah.library.service.LibrarianService;

@Service
public class LibrarianServiceImpl implements LibrarianService{
	
	@Autowired
	CustomDAO custom_dao;

	@Autowired
	BookLoansDAO book_loans_dao;

	@Autowired
	BorrowerDAO borrower_dao;

	@Autowired
	LibraryBranchDAO library_branch_dao;

	@Autowired
	BookDAO book_dao;
	
	@Override
	public List<Object[]> getBookAvailabilityByIdAndName(String book_id,String title,String author){
		List<Object[]> books= custom_dao.getByIdTitleAuthor(book_id, title,author);	
	return 	books;
	}
	
	
	@Override
	public boolean checkinNewBook(BookLoans newBook){
		return book_loans_dao.insert(newBook);	
	}
	

	@Override
	public BookLoans getIssuedBook(BookLoans findBook){
		return book_loans_dao.getIssuedBookInfo(findBook);	
	}
	
	@Override
	public void checkoutOldBook(BookLoans oldBook){
		book_loans_dao.remove(oldBook);	
	}
	
	@Override
	public boolean newBorrower(Borrower new_borrower){
		try {
		if(!borrower_dao.matchBorrower(new_borrower))
				return borrower_dao.insert(new_borrower);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public Borrower updateBorrower(Borrower borrower){
		return borrower_dao.update(borrower);	
	}
	@Override
	public Borrower searchBorrower(String card_no){
		return borrower_dao.find(card_no);	
	}
	
	@Override
	public void removeBorrower(String card_no){
		borrower_dao.remove(card_no);	
	}

	@Override
	public List<BookLoans> getAllIssuedCheckedinBooks() {
		return book_loans_dao.getAllIssuedBooks();
	}
	
	@Override
	public int validIssueStatusOfBorrower(Borrower borrower) {
		return borrower_dao.countNoOfBookByBorrower(borrower);
	}
	@Override
	public Borrower findBorrower(String cardNo) {
		return borrower_dao.find(cardNo);
	}
	
	@Override
	public boolean bookExists(String bookId) {
		return book_dao.isExists(bookId);
	}
	
	@Override
	public boolean branchExists(int branchId) {
		return library_branch_dao.isExist(branchId);
	}
	
	@Override
	public boolean borrowerExists(String cardNo) {
		return borrower_dao.isExist(cardNo);
	}
	
	@Override
	public List<Object[]> getIssuedBook(String bookId,String cardNo,String fName,String lName) {
		List<Object[]> books= custom_dao.getIssuedBook(bookId, cardNo, fName, lName);
		return books;
	}
	
	@Override
	public  boolean cheackBookAvailablility(String bookId,String branchId) {
		int booksleft =  custom_dao.getBookAvailabilityInBranch(bookId,branchId);
		if(booksleft > 0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean insertBranch(LibraryBranch branch){
		return library_branch_dao.insert(branch);
	}
	
	@Override
	public LibraryBranch searchBranch(int branchId){
		return library_branch_dao.find(branchId);
	}
	
	@Override
	public boolean searchBranch(LibraryBranch branch){
		return library_branch_dao.isExist(branch);
	}
	
	@Override
	public List<LibraryBranch> searchSelectedBranch(LibraryBranch branch){
		return library_branch_dao.searchBranchByQuery(branch);
	}
	
	@Override
	public boolean searchBranchByName(String branch_name){
		return library_branch_dao.isNameExists(branch_name);
	}
	
	@Override
	public LibraryBranch updateBranch(LibraryBranch branch){
		LibraryBranch updatedbranch = library_branch_dao.update(branch);
		return updatedbranch;
	}
	
	@Override
	public List<LibraryBranch> fetchAllBranches(){
		List<LibraryBranch> allbranch = library_branch_dao.getAllBranches();
		return allbranch;
	}
	
	@Override
	public void deleteBranch(int branch_id){
		System.out.println(branch_id);
		library_branch_dao.remove(branch_id); 
	}


	@Override
	public int getMaxCardNo() {
		return borrower_dao.fetchMaxCardId();
	}
	
	@Override
	public int getMaxBranchNo() {
		return library_branch_dao.fetchMaxBranchId();
	}
	
}
