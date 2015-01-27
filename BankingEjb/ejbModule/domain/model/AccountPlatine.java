package domain.model;

import javax.persistence.Entity;

/**
 * Entity implementation class for Entity: AccountPlatine
 *
 */
@Entity
public class AccountPlatine extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AccountPlatine(double decouvert) {
		super();
	    this.setOverdraftPenalty(decouvert);
	}   
	
	public AccountPlatine() {
		super();
	} 

}
