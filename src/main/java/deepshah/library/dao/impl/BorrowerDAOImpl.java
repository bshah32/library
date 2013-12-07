package deepshah.library.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import deepshah.library.dao.BorrowerDAO;
import deepshah.library.model.Borrower;
import deepshah.library.model.LibraryBranch;
import deepshah.library.model.impl.BookLoansImpl;
import deepshah.library.model.impl.BorrowerImpl;
import deepshah.library.model.impl.LibraryBranchImpl;

@Repository
public class BorrowerDAOImpl implements BorrowerDAO {

	@PersistenceContext(unitName = "companySetup")
	private EntityManager entityManager;

	// select * from borrower where card_no='?';
	@Override
	public Borrower find(String card_no) {
		Borrower borrower = entityManager.find(BorrowerImpl.class, card_no);
		if (borrower != null)
			return borrower;
		return null;
	}

	// insert into borrower (address, fname, lname, phone, card_no) values (?,
	// ?, ?, ?, ?);
	@Override
	@Transactional(value = "transactionManager", readOnly = false)
	public boolean insert(Borrower borrower) {
		if (!isExist(borrower.getCard_no())) {
			try {
				entityManager.persist(borrower);
			} catch (Exception e) {
				return false;
			}
			return true;
		} else
			return false;
	}

	// select EXISTS(select * from borrower where card_no='?'); it will return 0,1 if(0)==false if(1)==true
	@Override
	public boolean isExist(Borrower borrower) {
		return entityManager.contains(borrower);
	}

	// select EXISTS(select * from borrower where card_no='?'); it will return 0,1 if(0)==false if(1)==true	
	@Override
	public boolean isExist(String card_no) {
		Borrower borrower = entityManager.find(BorrowerImpl.class, card_no);
		if (borrower == null) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = false)
	public Borrower update(Borrower borrower) {
		if (isExist(borrower.getCard_no()))
			return entityManager.merge(borrower);
		return borrower;
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = false)
	public void remove(String card_no) {
		Borrower borrower = find(card_no);
		if (isExist(borrower.getCard_no()))
			entityManager.remove(borrower);
	}
// select * from borrower where fname='?' and lname='?' and address='?';
	@Override
	public boolean matchBorrower(Borrower borrower) {
		TypedQuery<BorrowerImpl> query = entityManager.createQuery("FROM BorrowerImpl c WHERE fname=:fName and lname=:lName and address=:Address and phone=:Phone", BorrowerImpl.class);
		query.setParameter("fName",borrower.getFname());
		query.setParameter("lName", borrower.getLname());
		query.setParameter("Address",borrower.getAddress() );
		query.setParameter("Phone", borrower.getPhone());
		 List<BorrowerImpl> fetchedBorrower = null;
		fetchedBorrower = query.getResultList();
		if(!fetchedBorrower.isEmpty()){
	 		 return true;
	 	 }
		 		 return false;	
	}
	
	@Override
	public int getBookIssuedByUser(Borrower borrower){
	return 0;
	}
	
	@Override
	public int countNoOfBookByBorrower(Borrower borrower){
		TypedQuery<BookLoansImpl> query = entityManager.createQuery("FROM BookLoansImpl c WHERE c.card_no=:cardNo", BookLoansImpl.class);
		query.setParameter("cardNo",borrower.getCard_no());
		List<BookLoansImpl> fetchedBooks = query.getResultList();
		return fetchedBooks.size();
	}
	
	@Override
	public int fetchMaxCardId(){
		int max_card_no = 0;
		Query query = entityManager.createNativeQuery("select MAX(card_no) from borrower");
		String output = String.valueOf(query.getSingleResult());
		if((output.equals("null") )|| (output.equals(""))){
		 return 0;
		}
		max_card_no =  Integer.valueOf(output);
		 System.out.println(max_card_no);
		return max_card_no ;
	}

	@Override
	public List<Borrower> getAllBorrower() {
		 TypedQuery<Borrower> query = entityManager.createNamedQuery("getAllBorrower", Borrower.class);
		 List borrowers = query.getResultList();
		 return borrowers;
		 
	}
	

}
