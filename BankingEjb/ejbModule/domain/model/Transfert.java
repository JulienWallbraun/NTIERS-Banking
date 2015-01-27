package domain.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "alltransferts", query = "select t FROM Transfert t"),
 @NamedQuery(name = "allTransfertbyAccount", query = "select t FROM Transfert t where t.cpteDebiteur = :cpteDebiteur")
})
public class Transfert {

	

	@Id
	@GeneratedValue
	private int id;
	
	
	private int cpteDebiteur;	
	private int cpteCrediteur;
	private double montant;
	private Date date;
	private String libele;



	public Transfert() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCpteDebiteur() {
		return cpteDebiteur;
	}

	public void setCpteDebiteur(int cpteDebiteur) {
		this.cpteDebiteur = cpteDebiteur;
	}

	public int getCpteCrediteur() {
		return cpteCrediteur;
	}

	public void setCpteCrediteur(int cpteCrediteur) {
		this.cpteCrediteur = cpteCrediteur;
	}
	
	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLibele() {
		return libele;
	}

	public void setLibele(String libele) {
		this.libele = libele;
	}
	
}
