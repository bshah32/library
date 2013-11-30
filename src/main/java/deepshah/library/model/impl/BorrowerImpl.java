package deepshah.library.model.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import deepshah.library.model.Borrower;
import deepshah.library.validator.Phone;

@Entity
@Table(name = "borrower")
@NamedQueries({ @NamedQuery(name = "fetchAllBorrowersQuery", query = "SELECT b FROM BorrowerImpl b")
// ,
// @NamedQuery(name="deleteBookByIdQuery",
// query="DELETE b FROM JPABookImpl b WHERE b.bookId =:bookId")
})
public class BorrowerImpl implements Borrower {

	@Id
	@NotNull @Size(min=1,max=10, message="Card No cannot be empty and should be less than 10")
	@Column(name = "card_no", nullable = false, length = 10)
	private String card_no;

	@NotNull @Size(min=1,max=25, message="First Name cannot be empty and should be less than 25")
	@Column(name = "fname", length = 25)
	private String fname;

	@NotNull @Size(min=1,max=25, message="Last Name cannot be empty and should be less than 25")
	@Column(name = "lname", length = 25)
	private String lname;

	@NotNull @Size(min=1,max=35, message="Address cannot be empty and should be less than 35")
	@Column(name = "address", length = 35)
	private String address;

	@NotNull @Size(min=10,max=15, message="Phone Number cannot be empty and should be between 10 to 15")
	@Phone
	@Column(name = "phone", length = 15)
	private String phone;

	@OneToMany(mappedBy = "card_no", targetEntity = BookLoansImpl.class)
	Set<BookLoansImpl> book_loans_set;

	@Override
	public String getCard_no() {
		return card_no;
	}

	@Override
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	@Override
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	@Override
	public String getLname() {
		return lname;
	}

	@Override
	public void setLname(String lname) {
		this.lname = lname;
	}

	@Override
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String getPhone() {
		return phone;
	}
	
	@Override
	public void setPhone(String phone) {
		this.phone = phone;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((card_no == null) ? 0 : card_no.hashCode());
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		result = prime * result + ((lname == null) ? 0 : lname.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		BorrowerImpl other = (BorrowerImpl) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (card_no == null) {
			if (other.card_no != null)
				return false;
		} else if (!card_no.equals(other.card_no))
			return false;
		if (fname == null) {
			if (other.fname != null)
				return false;
		} else if (!fname.equals(other.fname))
			return false;
		if (lname == null) {
			if (other.lname != null)
				return false;
		} else if (!lname.equals(other.lname))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

	/**
	 * @param card_no
	 * @param fname
	 * @param lname
	 * @param address
	 * @param phone
	 */
	public BorrowerImpl(String card_no, String fname, String lname,
			String address, String phone) {
		super();
		this.card_no = card_no;
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.phone = phone;
	}

	/**
	 * 
	 */
	public BorrowerImpl() {
		super();
	}

}
