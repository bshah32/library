package deepshah.library.dao;


import deepshah.library.model.LibraryBranch;

public interface LibraryBranchDAO {

	public LibraryBranch find(int branch_id);

	void insert(LibraryBranch branch);

	LibraryBranch update(LibraryBranch branch);

	boolean isExist(LibraryBranch branch);

	boolean isExist(int branchId);

		
	

}
