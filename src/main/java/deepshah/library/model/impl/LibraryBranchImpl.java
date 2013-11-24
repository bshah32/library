package deepshah.library.model.impl;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import deepshah.library.model.LibraryBranch;


@Entity
@Table(name="library_branch")
public class LibraryBranchImpl implements LibraryBranch{
	
	@Id 
    @Column(name="branch_id", nullable=false)
	private int branch_id=0;
 
    @Column(name="branch_name",length=25)
    private String branch_name;

    @Column(name="address",length=35)
    private String address;

    @OneToMany(mappedBy="branch_id", targetEntity = BookCopiesImpl.class)
	Set<BookCopiesImpl> book_copies_set;

    
    @OneToMany(mappedBy="branch_id", targetEntity = BookLoansImpl.class)
	Set<BookLoansImpl> book_loans_set;
    
    @Override
	public int getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}

	@Override
	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	@Override
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + branch_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LibraryBranchImpl other = (LibraryBranchImpl) obj;
		if (branch_id != other.branch_id)
			return false;
		return true;
	}

	/**
	 * @param branch_id
	 * @param branch_name
	 * @param address
	 */
	public LibraryBranchImpl(int branch_id, String branch_name, String address) {
		super();
		this.branch_id = branch_id;
		this.branch_name = branch_name;
		this.address = address;
	}

	/**
	 * 
	 */
	public LibraryBranchImpl() {
		super();
	}
    
	
    

}
