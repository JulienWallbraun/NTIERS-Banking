package beans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import tp.ejb.AccountDaoBean;
import domain.model.Account;

@ManagedBean(name = "depotRetraitWeb")
@SessionScoped
public class DepotRetraitWeb implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private AccountDaoBean compteDao;
	
	private Account compte;
	private double amount;
	private String operation;
	
	private String cpteId;
	
	public void retraitDepot(String id, String operation){
		compte = compteDao.findById(Integer.parseInt(id));
		this.operation =operation;
		
		if("Depot".equals(this.operation)){
			compteDao.creditAccount(compte, amount);
		}else
			if("Retrait".equals(this.operation)){
				compteDao.withdrawAccount(compte, amount);
			}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Votre "+this.operation+" s'est effectué avec succès", ""));
		MBUtils.redirect("accounts.xhtml");
	}
	
	public void reset(){
		//RequestContext.getCurrentInstance().reset("form:panel");
		amount = 0.0;
	}
	
	public Account getCompte() {
		return compte;
	}
	public void setCompte(Account compte) {
		this.compte = compte;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getCpteId() {
		return cpteId;
	}

	public void setCpteId(String cpteId) {
		this.cpteId = cpteId;
	}
	
	
	

}
