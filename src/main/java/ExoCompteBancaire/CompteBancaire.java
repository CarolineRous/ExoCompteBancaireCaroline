package ExoCompteBancaire;

import java.time.LocalDate;
import java.util.Objects;

public class CompteBancaire {
	int numCompte;
	String nomPropriétaire;
	double solde;
	LocalDate dateCreation;
	String typeCompte;

	public CompteBancaire(int numCompte, String nomPropriétaire, double solde, LocalDate dateCreation,
			String typeCompte) {
		super();
		this.numCompte = numCompte;
		this.nomPropriétaire = nomPropriétaire;
		this.solde = solde;
		this.dateCreation = dateCreation;
		this.typeCompte = typeCompte;
	}

	public CompteBancaire() {
		super();
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
		return "CompteBancaire [numCompte=" + numCompte + ", nomPropriétaire=" + nomPropriétaire + ", solde=" + solde
				+ ", dateCreation=" + dateCreation + ", typeCompte=" + typeCompte + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(numCompte);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompteBancaire other = (CompteBancaire) obj;
		return numCompte == other.numCompte;
	}

}
