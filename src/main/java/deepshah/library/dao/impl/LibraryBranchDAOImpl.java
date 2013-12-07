package deepshah.library.dao.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import deepshah.library.dao.LibraryBranchDAO;
import deepshah.library.model.LibraryBranch;
import deepshah.library.model.impl.LibraryBranchImpl;

@Repository
public class LibraryBranchDAOImpl implements LibraryBranchDAO {

	@PersistenceContext(unitName = "companySetup")
	private EntityManager entityManager;

	@Override
	public LibraryBranch find(int branch_id) {
		LibraryBranch branch = entityManager.find(LibraryBranchImpl.class,
				branch_id);
		if (branch == null)
			return null;
		return branch;
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = false)
	public boolean insert(LibraryBranch branch) {
		if (!this.isExist(branch.getBranch_id())) {
			entityManager.persist(branch);
			return true;
		}
		return false;
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = false)
	public boolean isExist(LibraryBranch branch) {
		return entityManager.contains(branch);
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = false)
	public boolean isExist(int branchId) {
		LibraryBranch branch = entityManager.find(LibraryBranchImpl.class, branchId);
		if (branch == null) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = false)
	public LibraryBranch update(LibraryBranch branch) {
		if (this.isExist(branch)) {
			return branch;
		}
		LibraryBranch updated_branch = entityManager.merge(branch);
		return updated_branch;
	}
	
	@Override
	@Transactional(value = "transactionManager", readOnly = false)
	public void remove(int branchId) {
		LibraryBranch branch = entityManager.find(LibraryBranchImpl.class, branchId);
		if (branch != null) {
			entityManager.remove(branch);
		}
	}



	 @Override
	 public List<LibraryBranch> getAllBranches() {
	 TypedQuery<LibraryBranchImpl> query = entityManager.createNamedQuery
	 ("getAllBranches", LibraryBranchImpl.class);
	 List branches = query.getResultList();
	 return branches;
	 }

	@Override
	public boolean isNameExists(String branch_name) {
		TypedQuery<LibraryBranchImpl> query = entityManager.createNamedQuery
				 ("searchByName", LibraryBranchImpl.class);
				query.setParameter("branchName", branch_name);
				 List<LibraryBranchImpl> branches = query.getResultList();
				  if(!branches.isEmpty())
				 return true;
		return false;
	}
	
	@Override
	public int fetchMaxBranchId(){
		int max_branch_no = 0;
		Query query = entityManager.createNativeQuery("select max(branch_id) from library_branch");
		String output = String.valueOf(query.getSingleResult());
		if((output.equals("null") )|| (output.equals(""))){
		 return 0;
		}
		 max_branch_no =  Integer.valueOf(output);
		return max_branch_no ;
	}

	@Override
	public List<LibraryBranch> searchBranchByQuery(LibraryBranch branch) {
		 Query query = entityManager.createNativeQuery("select * "
			 		+ "from library_branch "
			 		+ "WHERE branch_id LIKE '%"+branch.getBranch_id()+"%' AND branch_name LIKE '%"+branch.getBranch_name()+"%' AND address LIKE '%"+branch.getAddress()+"%'",LibraryBranchImpl.class);
		 List<LibraryBranch> result = query.getResultList();
		 return result;
	}
	 
}

