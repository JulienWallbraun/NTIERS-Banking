package tp.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import domain.model.Operation;

@Stateless
@LocalBean
public class OperationDaoBean implements OperationDao {
	


		@PersistenceContext
		EntityManager em;
	    /**
	     * Default constructor. 
	     */
	    public OperationDaoBean() {
	        // TODO Auto-generated constructor stub
	    }
	    
	    
		@Override
		public Operation ajouterOperation(Operation op) {
			// TODO Auto-generated method stub
			em.persist(op);
			return op;
		}
		
		@Override
		public void supprimerOperation(Operation op) {
			// TODO Auto-generated method stub
			Operation ope = em.find(Operation.class, op.getId());
			em.remove(ope);
		}
		
		@Override
		public void modifierOperation(Operation op){
			// TODO Auto-generated method stub
			Operation o = em.find(Operation.class, op.getId());
			if(o!=null) em.merge(op);
		}
		
		@Override
		public Operation rechercherOperation(int id){
			// TODO Auto-generated method stub
			Operation op = em.find(Operation.class, id);
			 return op;
					
		}

	    
	   
	}


