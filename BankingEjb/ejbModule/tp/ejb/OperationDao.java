package tp.ejb;

import javax.ejb.Remote;

import domain.model.Operation;

@Remote
public interface OperationDao {

	Operation ajouterOperation(Operation op);

	void supprimerOperation(Operation op);

	void modifierOperation(Operation op) ;

	Operation rechercherOperation(int id);

}
