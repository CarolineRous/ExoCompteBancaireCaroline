package ExoCompteBancaire;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class CreateWriteFromKeyboard {
	public static void main(String[] args) {
		try {
			Document doc = new Document();
			doc.setRootElement(new Element("CompteBancaires"));

			try (Scanner scanner = new Scanner(System.in)) {
				System.out.println("Combien de compte souhaitez-vous créer : ");
				int nbCompte = getNbCompte(scanner);
				for (int i = 0; i < nbCompte; i++) {
					System.out.println("Création du compte n°" + (i + 1) + " : ");
					CompteBancaire compte = CreationCompteBancaireFromKeyboard(scanner);
					doc.getRootElement().addContent(createCompteBancaireXMLElement(compte));
					System.out.println("Le compte " + (i + 1) + " a été créé.");
				}
			}

			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("compteBancaires.xml"));
			System.out.println("File Saved!");
		} catch (IOException io) {
			System.out.println(io.getMessage());
		}
	}

	private static Element createCompteBancaireXMLElement(CompteBancaire compte) {
		Element compteElement = new Element("CompteBancaire");
		compteElement.addContent(new Element("numCompte").setText("" + compte.getNumCompte()));
		compteElement.addContent(new Element("nomPropriétaire").setText(compte.getNomPropriétaire()));
		compteElement.addContent(new Element("solde").setText("" + compte.getSolde()));
		compteElement.addContent(new Element("dateCreation").setText("" + compte.getDateCreation()));
		compteElement.addContent(new Element("typeCompte").setText(compte.getTypeCompte()));
		return compteElement;
	}

	private static String getString(Scanner scanner) {
		try {
			return scanner.nextLine();
		} catch (Exception e) {
			scanner.nextLine();
			System.err.println("Erreur de saisie. Veuillez réessayer.");
			return getString(scanner);
		}
	}

	private static int getInt(Scanner scanner) {
		try {
			return scanner.nextInt();
		} catch (Exception e) {
			scanner.nextLine();
			System.err.println("Erreur de saisie. Veuillez réessayer.");
			return getInt(scanner);
		}
	}

	private static LocalDate getDate(Scanner clavier) {
		try {
			System.out.println("En commençant par saisir l'année : ");
			int year = clavier.nextInt();
			clavier.nextLine();
			System.out.println("Veuillez saisir le mois : ");
			int month = clavier.nextInt();
			clavier.nextLine();
			System.out.println("Veuillez saisir le jour : ");
			int day = clavier.nextInt();
			return LocalDate.of(year, month, day);
		} catch (Exception e) {
			clavier.nextLine();
			System.err.println("Erreur de saisie. Veuillez réessayer.");
			return getDate(clavier);
		}
	}

	private static String getTypeCompte(Scanner scanner) {
		String typeCompte = getString(scanner);
		if (typeCompte.equalsIgnoreCase("courant")) {
			return typeCompte;
		} else if (typeCompte.equalsIgnoreCase("épargne")) {
			return typeCompte;
		} else {
			System.err.println("Erreur de saisie. Veuillez réessayer.");
			return getTypeCompte(scanner);
		}
	}

	private static int getNbCompte(Scanner scanner) {
		int nbCompte = getInt(scanner);
		if (nbCompte <= 0) {
			System.out.println("Veuillez entrer un nombre positif");
			return getNbCompte(scanner);
		} else if (nbCompte > 10) {
			System.out.println("Il est impossible d'ajouter plus de 10 comptes en banque !");
			return getNbCompte(scanner);
		} else {
			return nbCompte;
		}
	}

	private static CompteBancaire CreationCompteBancaireFromKeyboard(Scanner scanner) {
		System.out.println("Pour enregistrer un nouveau compte, veuillez entrer le numéro du compte : ");
		int numCompte = getInt(scanner);
		scanner.nextLine();
		System.out.println("Veuillez entrer le nom du propriétaire : ");
		String nomPropriétaire = getString(scanner);
		scanner.nextLine();
		System.out.println("Veuillez entrer le solde du compte : ");
		int solde = getInt(scanner);
		scanner.nextLine();
		System.out.println("Veuillez entrer la date de création du compte : ");
		LocalDate dateCreation = getDate(scanner);
		scanner.nextLine();
		System.out.println("Veuillez préciser s'il s'agit d'un compte épargne ou courant : ");
		String typeCompte = getTypeCompte(scanner);
		scanner.nextLine();
		return new CompteBancaire(numCompte, nomPropriétaire, solde, dateCreation, typeCompte);
	}
}
