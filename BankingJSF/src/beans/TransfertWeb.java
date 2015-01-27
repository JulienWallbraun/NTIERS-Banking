package beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import tp.ejb.AccountDaoBean;
import tp.ejb.BankDaoBean;
import tp.ejb.CustomerDaoBean;
import tp.ejb.TransfertDaoBean;
import domain.model.Account;
import domain.model.City;
import domain.model.Customer;


@ManagedBean(name = "transfert")
@SessionScoped
public class TransfertWeb implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	AccountDaoBean compteDao;
	
	@EJB
	CustomerDaoBean clientDao;
	
	@EJB
	TransfertDaoBean tranfertDao;
	
	private Map<String, String> accountMap = null;
	private String numCpte1;
	private String numCpte2;
	
	
    private double montant;
    private String libele;
    private Account compteDebiteur;
    private Account compteCrediteur;
    
    
	
	
	public Map<String, String> getAccounts(String name){
		
		//if (accountMap == null){
			accountMap = new HashMap();
			Customer client = clientDao.findByName(name);
			List<Account> accounts = compteDao.getList(client);
			for (Account compte : accounts) 
				accountMap.put(compte.getAccountNumber(), Integer.toString(compte.getId()));
		//}
		return accountMap;
	}
	
	public Map<String, String> getAllAccounts(){
		
		//if (accountMap == null){
			accountMap = new HashMap();
			List<Account> accounts = compteDao.getList();
			for (Account compte : accounts) 
				accountMap.put(compte.getAccountNumber(), Integer.toString(compte.getId()));
		//}
		return accountMap;
	}
	
	public String envoie(String numCompte1, String numCompte2){
		compteDebiteur = compteDao.findById(Integer.parseInt(numCompte1));
		compteCrediteur = compteDao.findById(Integer.parseInt(numCompte2));
		
		tranfertDao.newTransfert(compteDebiteur, compteCrediteur, montant, libele);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Opération effectuée avec succès", ""));
		MBUtils.redirect("accounts.xhtml");
		return "envoyé";
	}
	
	public void reset(){
		//RequestContext.getCurrentInstance().reset("form:panel");
		montant = 0.0;
		libele = null;
	}
	
	
	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getLibele() {
		return libele;
	}

	public void setLibele(String libele) {
		this.libele = libele;
	}

	public Account getCompteDebiteur() {
		return compteDebiteur;
	}

	public void setCompteDebiteur(Account compteDebiteur) {
		this.compteDebiteur = compteDebiteur;
	}

	public Account getCompteCrediteur() {
		return compteCrediteur;
	}

	public void setCompteCrediteur(Account compteCrediteur) {
		this.compteCrediteur = compteCrediteur;
	}

	public String getNumCpte1() {
		return numCpte1;
	}

	public void setNumCpte1(String numCpte1) {
		this.numCpte1 = numCpte1;
	}

	public String getNumCpte2() {
		return numCpte2;
	}

	public void setNumCpte2(String numCpte2) {
		this.numCpte2 = numCpte2;
	}

	public TransfertWeb() {
		// TODO Auto-generated constructor stub
	}

	
	
	
}
