package tp.ejb;

import java.util.List;

import javax.ejb.Remote;

import domain.model.Account;
import domain.model.Transfert;

@Remote
public interface TransfertDao {

	List<Transfert> getList(Account ac);
	Transfert find(int id);
	void delete(Transfert t);
	Transfert add(Transfert t);
	void merge(Transfert t);
	void newTransfert(Account from, Account to, double amount, String motif);
	
}
