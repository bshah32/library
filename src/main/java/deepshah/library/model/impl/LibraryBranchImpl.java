package deepshah.library.model.impl;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import deepshah.library.model.LibraryBranch;

@Entity
@Table(name = "library_branch")
@NamedQueries({ @NamedQuery(name = "getAllBranches", query = "SELECT c FROM LibraryBranchImpl c"),
	@NamedQuery(name = "searchByName", query = "SELECT c FROM LibraryBranchImpl c WHERE c.branch_name=:branchName"),})
public class LibraryBranchImpl implements LibraryBranch {

	@Id
	@Column(name = "branch_id", nullable = false,columnDefinition="int(5) default '0'")
	@NumberFormat(style=Style.NUMBER)
	@Min(1)
	private int branch_id;

	@Column(name = "branch_name", length = 25)
	@Size(min=1,max=25,message="'Branch Name' cannot be empty and should be less than 25")
	private String branch_name;

	@Column(name = "address", length = 35)
	@Size(min=1,max=35,message="Address cannot be empty and should be less than 35")
	private String address;

	@OneToMany(mappedBy = "branch_id", targetEntity = BookCopiesImpl.class,cascade={CascadeType.ALL})
	Set<BookCopiesImpl> book_copies_set;

	@OneToMany(mappedBy = "branch_id", targetEntity = BookLoansImpl.class,cascade={CascadeType.ALL})
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



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + branch_id;
		result = prime * result
				+ ((branch_name == null) ? 0 : branch_name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof LibraryBranchImpl)) {
			return false;
		}
		LibraryBranchImpl other = (LibraryBranchImpl) obj;
		if (address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!address.equals(other.address)) {
			return false;
		}
		if (branch_id != other.branch_id) {
			return false;
		}
		if (branch_name == null) {
			if (other.branch_name != null) {
				return false;
			}
		} else if (!branch_name.equals(other.branch_name)) {
			return false;
		}
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
