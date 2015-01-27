package components;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import beans.CustomerWeb;
import beans.MBUtils;
import beans.BankWeb;
import domain.model.Customer;
import tp.ejb.BankDaoBean;
import tp.ejb.CustomerDaoBean;

@ManagedBean(name = "login")
@SessionScoped
public class LoginView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstname;
	private String password;

	@EJB
	CustomerDaoBean clientDao;
	
	@EJB
	BankDaoBean bankDao;
	
	@ManagedProperty(value="#{customerWeb}")
	private CustomerWeb custweb;
	
	@ManagedProperty(value="#{bankWeb}")
	private BankWeb banweb;

	public BankWeb getBanweb() {
		return banweb;
	}

	public void setBanweb(BankWeb banweb) {
		this.banweb = banweb;
	}

	public CustomerWeb getCustweb() {
		return custweb;
	}

	public void setCustweb(CustomerWeb custweb) {
		this.custweb = custweb;
	}

	private Customer currentCustomer;
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String lastname) {
		this.password = lastname;
	}

	public Customer getCurrentCustomer() {
		return currentCustomer;
	}

	public void setCurrentCustomer(Customer currentCustomer) {
		this.currentCustomer = currentCustomer;
	}

	int i =0;
	
	public void connect(ActionEvent event) {
		FacesMessage msg = null;
		
		if(i!=1){
		bankDao.populate();
		i=1;
		}		
		currentCustomer = clientDao.findByName(firstname);
		if (currentCustomer==null) 
		{
			
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error : "+
					" Le client " + firstname + " est inexistant", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		else{
			if (password.equals(currentCustomer.getPassword())) {
				//connexion reussie				
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome "+ firstname,
						firstname);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			custweb.setCurrentCustomer(currentCustomer);
			banweb.setCurrentBank(currentCustomer.getBank());
				MBUtils.redirect("banks.xhtml");
			} else {				
				msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
						 "Login Error : "+"Mot de passe incorrect!!","" );
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}  
		
		
	}
	
	public void test(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Test", ""));
	}
	public void deconnect(ActionEvent event){
		if(currentCustomer!=null){
		  currentCustomer = null;
          custweb.setCurrentCustomer(currentCustomer);
		MBUtils.redirect("login.xhtml");
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Vous devez vous connecter avant", ""));
		}
	}

}
