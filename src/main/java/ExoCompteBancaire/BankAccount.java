package ExoCompteBancaire;

import java.time.LocalDate;
import java.util.Objects;

public class BankAccount {
	int numCompte;
	String nomPropriétaire;
	double solde;
	LocalDate dateCreation;
	String typeCompte;

	public BankAccount(int numCompte, String nomPropriétaire, double solde, LocalDate dateCreation, String typeCompte) {
		super();
		this.numCompte = numCompte;
		this.nomPropriétaire = nomPropriétaire;
		this.solde = solde;
		this.dateCreation = dateCreation;
		this.typeCompte = typeCompte;
	}

	public BankAccount() {
		super();
	}

	public BankAccount(BankAccount compte) {
		super();
		this.numCompte = compte.numCompte;
		this.nomPropriétaire = compte.nomPropriétaire;
		this.solde = compte.solde;
		this.dateCreation = compte.dateCreation;
		this.typeCompte = compte.typeCompte;
	}

	public int getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(int numCompte) {
		this.numCompte = numCompte;
	}

	public String getNomPropriétaire() {
		return nomPropriétaire;
	}

	public void setNomPropriétaire(String nomPropriétaire) {
		this.nomPropriétaire = nomPropriétaire;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public LocalDate getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getTypeCompte() {
		return typeCompte;
	}

	public void setTypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
	}

	@Override
	public String toString() {
		return "BankAccount [numCompte=" + numCompte + ", nomPropriétaire=" + nomPropriétaire + ", solde=" + solde
				+ ", dateCreation=" + dateCreation + ", typeCompte=" + typeCompte + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccount other = (BankAccount) obj;
		return Objects.equals(nomPropriétaire, other.nomPropriétaire) && numCompte == other.numCompte;
	}

}
