package deepshah.library.test;

import java.sql.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import deepshah.library.dao.LibraryBranchDAO;
import deepshah.library.model.BookLoans;
import deepshah.library.model.LibraryBranch;
import deepshah.library.model.impl.BookLoansImpl;
import deepshah.library.model.impl.BorrowerImpl;
import deepshah.library.model.impl.LibraryBranchImpl;
import deepshah.library.service.LibrarianService;

@ContextConfiguration(locations = "classpath:test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CompanyDaoTest {

	@Autowired
	LibraryBranchDAO librarybranchDao;

	@Autowired
	LibrarianService librarianService;

	@Test
	public void testSaveCompanyAndDepartment() {

		LibraryBranch branch = librarybranchDao.find(3);
		Assert.assertNotNull(branch);
		System.out.println("Branch : " + branch.getBranch_name());
		branch = new LibraryBranchImpl(6, "Deep Shah", "Hello How Are You");
		librarybranchDao.insert(branch);
		branch = new LibraryBranchImpl(6, "Deep Shah", "Updated");
		librarybranchDao.update(branch);
//		List<LibraryBranch> list = librarybranchDao.getAllBranches();
//
//		System.out.println("List size is : " + list.size());
//
//		for (LibraryBranch objects : list)
//		{ 
//		String groupname = objects.getAddress();
//		String friendname = objects.getBranch_name();
//		System.out.println( " " + groupname + "	|	"+friendname);
//		}
//		List<Object[]> list1 = librarianService.getBookAvailabilityByName("Stranger");
//
//		System.out.println("List size is : " + list1.size());
//		for (Object[] result : list1) {
//			for(int i = 0; i<result.length;i++)
//				System.out.print("\t" + result[i]);
//			System.out.println("\n");
//		  }
		
		BookLoansImpl b = new BookLoansImpl("967174805",3, "9041",Date.valueOf("2013-11-28"),Date.valueOf("2013-11-28"));
		librarianService.checkinNewBook(b);
		BookLoans book = librarianService.getIssuedBook(b);
		System.out.println(book.getBook_id());
		
		BorrowerImpl bow = new BorrowerImpl("0000","Deep","Shah","Texas", "9725108115");
		librarianService.newBorrower(bow);
		bow.setAddress("Vadodara");
		librarianService.updateBorrower(bow);
		librarianService.removeBorrower(b.getCard_no());
	}

	@Test
	@Ignore
	public void testFindCompany() {

	}

}
