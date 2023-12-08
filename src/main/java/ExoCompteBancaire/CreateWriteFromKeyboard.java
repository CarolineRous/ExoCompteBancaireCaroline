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

	public static void createCompteBancaire(Scanner scanner) {
		try {
			Document doc = new Document();
			doc.setRootElement(new Element("CompteBancaires"));
			System.out.println("Création d'un nouveau compte bancaire : ");
			CompteBancaire compte = CreationCompteBancaireFromKeyboard(scanner);
			doc.getRootElement().addContent(createCompteBancaireXMLElement(compte));
			System.out.println("Le compte " + compte.getNumCompte() + " a été créé.");

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

	private static String getTypeCompte(Scanner scanner) {
		String typeCompte = GestionScanner.getString(scanner);
		if (typeCompte.equalsIgnoreCase("courant")) {
			return typeCompte;
		} else if (typeCompte.equalsIgnoreCase("épargne")) {
			return typeCompte;
		} else {
			System.err.println("Erreur de saisie. Veuillez réessayer.");
			return getTypeCompte(scanner);
		}
	}

	private static CompteBancaire CreationCompteBancaireFromKeyboard(Scanner scanner) {
		System.out.println("Pour enregistrer un nouveau compte, veuillez entrer le numéro du compte : ");
		int numCompte = GestionScanner.getInt(scanner);
		scanner.nextLine();
		System.out.println("Veuillez entrer le nom du propriétaire : ");
		String nomPropriétaire = GestionScanner.getString(scanner);
		scanner.nextLine();
		System.out.println("Veuillez entrer le solde du compte : ");
		int solde = GestionScanner.getInt(scanner);
		scanner.nextLine();
		System.out.println("Veuillez entrer la date de création du compte : ");
		LocalDate dateCreation = GestionScanner.getDate(scanner);
		scanner.nextLine();
		System.out.println("Veuillez préciser s'il s'agit d'un compte épargne ou courant : ");
		String typeCompte = getTypeCompte(scanner);
		scanner.nextLine();
		return new CompteBancaire(numCompte, nomPropriétaire, solde, dateCreation, typeCompte);
	}
}
