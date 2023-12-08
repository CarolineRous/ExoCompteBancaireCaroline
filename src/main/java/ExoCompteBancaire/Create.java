package ExoCompteBancaire;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Create {

	public static void createBankAccount(Scanner scanner) {

		final String fileName = "CompteBancaires.xml";
		try {
			// désérialisation du ficher XML
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(fileName);
			Document jdomDoc = (Document) builder.build(xmlFile);

			// création d'un nouveau compte bancaire et ajout au jdomDoc
			System.out.println("Création d'un nouveau compte bancaire : ");
			BankAccount compte = infoEntries(scanner);
			jdomDoc.getRootElement().addContent(createBankAccountXMLElement(compte));
			System.out.println("Le compte " + compte.getNumCompte() + " a été créé.");
			System.out.println(compte);

			// sérialisation du fichier XML
			XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
			xmlOutput.output(jdomDoc, new FileWriter("CompteBancaires.xml"));
			System.out.println("File Saved!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static Element createBankAccountXMLElement(BankAccount compte) {
		Element compteElement = new Element("BankAccount");
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
		} else if (typeCompte.equalsIgnoreCase("epargne")) {
			return typeCompte;
		} else {
			System.err.println("Erreur de saisie. Veuillez réessayer.");
			return getTypeCompte(scanner);
		}
	}

	static BankAccount infoEntries(Scanner scanner) {
		System.out.println("Pour enregistrer un nouveau compte, veuillez entrer le numéro du compte : ");
		int numCompte = GestionScanner.getInt(scanner);
		if (checkExist(numCompte, scanner)) {
			return new BankAccount(infoEntriesNext(scanner, numCompte));
		} else {
			return infoEntries(scanner);
		}
	}

	static BankAccount infoEntriesNext(Scanner scanner, int numCompte) {
		scanner.nextLine();
		System.out.println("Veuillez entrer le nom du propriétaire : ");
		String nomPropriétaire = GestionScanner.getString(scanner);
		System.out.println("Veuillez entrer le solde du compte : ");
		Double solde = GestionScanner.getDouble(scanner);
		scanner.nextLine();
		System.out.println("Veuillez entrer la date de création du compte : ");
		LocalDate dateCreation = GestionScanner.getDate(scanner);
		scanner.nextLine();
		System.out.println("Veuillez préciser s'il s'agit d'un compte épargne ou courant : ");
		String typeCompte = getTypeCompte(scanner);
		return new BankAccount(numCompte, nomPropriétaire, solde, dateCreation, typeCompte);
	}

	private static boolean checkExist(int numCompte, Scanner scanner) {
		List<BankAccount> compteList = ReadList.read(scanner);
		for (BankAccount compte : compteList) {
			if (compte.getNumCompte() == numCompte) {
				System.out.println("Ce numero de compte existe déjà !");
				return false;
			}
		}
		return true;
	}
}
