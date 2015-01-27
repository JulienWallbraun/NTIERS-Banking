package beans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tp.ejb.AccountDaoBean;
import domain.model.Account;

@ManagedBean(name = "compteWeb")
@SessionScoped
public class AccountWeb implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private AccountDaoBean compteDao;
	
	private Account currentAccount;
	
	private List<Account> comptes;
	
	
	public List<Account> getAllAccounts(){
		comptes = compteDao.getList();
		if(!comptes.isEmpty() && currentAccount==null)
			currentAccount = comptes.get(0);
		return comptes;
	}
	
	public String next() {
		setCurrentAccount(compteDao.next(getCurrentAccount().getOwner(), getCurrentAccount()));
		return "accounts";
	}
	
	public boolean isCurrentAccount(Account compte){
		return compte.equals(currentAccount);
	}
		
	public Account getCurrentAccount() {
		if(currentAccount==null)
			getAllAccounts();
		return currentAccount;
	}

	public void setCurrentAccount(Account currentAccount) {
		this.currentAccount = currentAccount;
	}

	public List<Account> getComptes() {
		return comptes;
	}

	public void setComptes(List<Account> comptes) {
		this.comptes = comptes;
	}

}
