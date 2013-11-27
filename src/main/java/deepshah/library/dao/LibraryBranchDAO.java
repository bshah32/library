package deepshah.library.dao;

import java.util.List;

import deepshah.library.model.LibraryBranch;

public interface LibraryBranchDAO {

	void insert(LibraryBranch branch);

	LibraryBranch update(LibraryBranch branch);

	boolean isExist(LibraryBranch branch);

	boolean isExist(int branchId);

	public List<LibraryBranch> getAllBranches();

	LibraryBranch find(int branch_id);

}
