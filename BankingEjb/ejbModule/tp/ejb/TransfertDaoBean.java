package tp.ejb;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import domain.model.Account;
import domain.model.Transfert;


@Stateless
@LocalBean
public class TransfertDaoBean implements TransfertDao, Serializable {
	
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	AccountDaoBean compteDao;
	

	@Override
	public List<Transfert> getList(Account ac) {
	   return em.createNamedQuery("allTransfertbyAccount").setParameter("cpteDebiteur", ac).getResultList();
	}

	@Override
	public Transfert find(int id) {
		// TODO Auto-generated method stub
		Transfert t = em.find(Transfert.class, id);
		return t;
	}

	@Override
	public void delete(Transfert t) {
		// TODO Auto-generated method stub
		em.remove(find(t.getId()));
	}

	@Override
	public void merge(Transfert t) {
		// TODO Auto-generated method stub
		Transfert tr = em.find(Transfert.class, t.getId());
		if(tr!=null)
			em.merge(t);		
	}

	@Override
	public void newTransfert(Account from, Account to, double amount, String libele) {
		// TODO Auto-generated method stub
		
		if (amount <= 0)
			throw new RuntimeException("amount is null or negative");
		if (from.getBalance() - amount < -from.getOverdraftLimit())
			throw new RuntimeException("overdraft limit exceeded !");
		Transfert tr = new Transfert();
		
		compteDao.withdrawAccount(from, amount);
		compteDao.creditAccount(to, amount);
//		
//		from.setBalance(from.getBalance() - amount);
//		to.setBalance(to.getBalance() + amount);
//		
//		
//		compteDao.merge(from.getOwner(), from);
//		compteDao.merge(to.getOwner(), to);
		
		tr.setCpteCrediteur(to.getId());
		tr.setCpteDebiteur(from.getId());
		tr.setDate(new Date());
		tr.setMontant(amount);
		tr.setLibele(libele);
		add(tr);
		
	}

	@Override
	public Transfert add(Transfert t) {
		// TODO Auto-generated method stub
		em.persist(t);
		return t;
	}
	
}
