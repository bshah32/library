package deepshah.library.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;
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
	public void insert(LibraryBranch branch) {
		if (!this.isExist(branch.getBranch_id())) {
			entityManager.persist(branch);
		}
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = false)
	public boolean isExist(LibraryBranch branch) {
		return entityManager.contains(branch);
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = false)
	public boolean isExist(int branchId) {
		LibraryBranch branch = this.find(branchId);
		if (branch == null) {
			return false;
		}
		return entityManager.contains(branch);
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
	 public List<LibraryBranch> getAllBranches() {
	 TypedQuery<LibraryBranchImpl> query = entityManager.createNamedQuery
	 ("getAllBranches", LibraryBranchImpl.class);
	 List branches = query.getResultList();
	 return branches;
	 }

}
