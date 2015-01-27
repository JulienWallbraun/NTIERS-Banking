import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import domain.model.Account;
import domain.model.Bank;
import domain.model.Customer;
import tp.ejb.AccountDao;
import tp.ejb.BankDao;
import tp.ejb.CityDao;
import tp.ejb.CustomerDao;
import tp.ejb.TransfertDao;




public class Main {
	private AccountDao compteDao;
	private CustomerDao clientDao;
	private BankDao bankDao;
	private CityDao cityDao;
	private TransfertDao transfertDao;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		Main client = new Main();
		try {
			if (client.connexion())
				client.executer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}
	
	
	boolean connexion(){
		final Hashtable jndiProperties = new Hashtable();
			jndiProperties.put(Context.PROVIDER_URL, "remote://localhost:4447");
			jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			jndiProperties.put(Context.SECURITY_PRINCIPAL,"david");
		    jndiProperties.put(Context.SECURITY_CREDENTIALS, "azertyuiop");
		    jndiProperties.put("jboss.naming.client.ejb.context", true);
		    	try {
		    		Context context = new InitialContext(jndiProperties);
		    		 
		    		compteDao = (AccountDao)context.
		    				lookup("BankingEAR/BankingEjb/AccountDaoBean!tp.ejb.AccountDao");
		    		 clientDao = (CustomerDao)context.
			    				lookup("BankingEAR/BankingEjb/CustomerDaoBean!tp.ejb.CustomerDao");
		    		 cityDao = (CityDao)context.
			    				lookup("BankingEAR/BankingEjb/CityDaoBean!tp.ejb.CityDao");
		    		 
		    		 transfertDao = (TransfertDao)context.
			    				lookup("BankingEAR/BankingEjb/TransfertDaoBean!tp.ejb.TransfertDao");
		    		 
		    		 bankDao = (BankDao)context.
			    				lookup("BankingEAR/BankingEjb/BankDaoBean!tp.ejb.BankDao");  		 
		    		     		 
		    		
		    		
		    	
		    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);return false;
		    		}
				return true;
		    
	}
	
	private void executer(){
		System.out.println("Debut");
	
		
		cityDao.populate();
		
		Bank b = bankDao.create();
		b.setName("Crédit Arboricole");
		b.setPhone("123456");
		b.setAddress( "3, rue des sapins");
		b.setZipCode("75000");
		bankDao.merge(b);
		clientDao.populate(b, 0);
		
		b = bankDao.create();
		b.setName("Internet Bank");
		b.setPhone("7890123");
		b.setAddress( "10, avenue de la Californie");
		b.setZipCode("13000");
		bankDao.merge(b);
		clientDao.populate(b, 1);
	

		b = bankDao.create();
		b.setName("Société Géniale");
		b.setPhone("45698741");
		b.setAddress( "1, parvis de la Défonse");
		b.setZipCode("92400");
		bankDao.merge(b);
		
//		Account ac = new Account("1230", null,100, 45, 150, 200);
		Customer c= clientDao.add(new Customer("Davids", "Miller", "12 rue matisse", "30100", "DavidsM"));
		Account ac =compteDao.create(c);
		ac.setBalance(120);
		ac.setInterestRate(100);
		ac.setOverdraftLimit(45);
		ac.setOverdraftPenalty(150);		
		compteDao.merge(c, ac);
		
		Account ac1 = compteDao.create(c);
		
		transfertDao.newTransfert(ac, ac1, 100, "Test");		
		
		
		System.out.println("Fin");
	}

}