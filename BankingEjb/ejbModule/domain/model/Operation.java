package domain.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Operation
 *
 */
@Entity

public class Operation implements Serializable {

	   
	@Id
	@GeneratedValue
	private int id;
	private Date date;
	private TypeOperation typeOperation;
	private double montant;
	
	@ManyToOne
	@JoinColumn(name="compte_Id")
	private Account compte;
	private static final long serialVersionUID = 1L;

	public Operation() {
		super();
	}   
	
	public Operation(Account compte, Date d, TypeOperation type, double montant){
		this.date =d;
		this.typeOperation = type;
		this.montant = montant;
		this.compte = compte;
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}   
	 
	public TypeOperation getTypeOperation() {
		return typeOperation;
	}

	public void setTypeOperation(TypeOperation typeOperation) {
		this.typeOperation = typeOperation;
	}

	public double getMontant() {
		return this.montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}
   
}
