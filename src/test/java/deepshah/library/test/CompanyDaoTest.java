package deepshah.library.test;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import deepshah.library.dao.LibraryBranchDAO;
import deepshah.library.model.LibraryBranch;
import deepshah.library.model.impl.LibraryBranchImpl;

@ContextConfiguration(locations = "classpath:test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CompanyDaoTest {

	@Autowired
	LibraryBranchDAO librarybranchDao;

	@Test
	public void testSaveCompanyAndDepartment() {

		LibraryBranch branch =  librarybranchDao.find(3);
		Assert.assertNotNull(branch);
		System.out.println("Branch : "+branch.getBranch_name());
		branch = new LibraryBranchImpl(6,"Deep Shah","Hello How Are You");
		librarybranchDao.insert(branch);
		branch = new LibraryBranchImpl(6,"Deep Shah","Updated");
		librarybranchDao.update(branch);
	}
	
	@Test
	@Ignore
	public void testFindCompany() {

	}

}
