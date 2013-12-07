package deepshah.library.dao;

import java.util.List;

import deepshah.library.model.LibraryBranch;

public interface LibraryBranchDAO {

	boolean insert(LibraryBranch branch);

	LibraryBranch update(LibraryBranch branch);

	boolean isExist(LibraryBranch branch);

	boolean isExist(int branchId);

	public List<LibraryBranch> getAllBranches();

	LibraryBranch find(int branch_id);

	boolean isNameExists(String branch_name);

	void remove(int branchId);

	int fetchMaxBranchId();

	List<LibraryBranch> searchBranchByQuery(LibraryBranch branch);
}
