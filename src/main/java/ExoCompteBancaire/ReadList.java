package ExoCompteBancaire;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class ReadList {

	static void choixListeCompteBancaire(Scanner scanner) {
		List<CompteBancaire> compteList = read(scanner);
		System.out.println("Veuillez choisir parmi les options suivantes : ");
		System.out.println("Liste de tous les comptes bancaires : tapez 1 .");
		System.out.println("Liste des comptes bancaires courants : tapez 2.");
		System.out.println("Liste des comptes bancaires épargnes : tapez 3.");
		int choice = getChoiceList(scanner);
		switch (choice) {
		case 1:
			for (CompteBancaire compte : compteList) {
				System.out.println(compte);
			}
			break;
		case 2:
			for (CompteBancaire compte : compteList) {
				if (compte.getTypeCompte().equals("courant")) {
					System.out.println(compte);
				}
			}
			break;
		case 3:
			for (CompteBancaire compte : compteList) {
				if (compte.getTypeCompte().equals("épargne")) {
					System.out.println(compte);
				}
			}
			break;
		}
		System.out.println("Fin de la liste des comptes bancaires.");
	}

	public static void afficherCompteBancaire(Scanner scanner) {
		List<CompteBancaire> compteList = read(scanner);
		System.out.println("Voulez vous chercher une compte par : ");
		System.out.println("1. Numéro de compte");
		System.out.println("2. Nom du propriétaire");
		int choice = getChoiceAffichage(scanner);
		switch (choice) {
		case 1:
			System.out.println("Veuillez sasir le numéro du compte recherché : ");
			int numCompte = GestionScanner.getInt(scanner);
			for (CompteBancaire compte : compteList) {
				if (compte.getNumCompte() == numCompte) {
					System.out.println(compte);
					break;
				}
			}
		case 2:
			System.out.println("Veuillez sasir le nom du propriétaire recherché : ");
			String nomPropriétaire = GestionScanner.getString(scanner);
			for (CompteBancaire compte : compteList) {
				if (compte.getNomPropriétaire().equals(nomPropriétaire)) {
					System.out.println(compte);
				}
			}
			break;
		}

	}

	private static int getChoiceAffichage(Scanner scanner) {
		int choice = GestionScanner.getInt(scanner);
		if (choice == 1 || choice == 2) {
			return choice;
		} else {
			System.err.println("Erreur de saisie. Veuillez saisir un choix correct.");
			return getChoiceAffichage(scanner);
		}
	}

	public static List<CompteBancaire> read(Scanner scanner) {
		final String fileName = "CompteBancaires.xml";
		List<CompteBancaire> compteList = new ArrayList<CompteBancaire>();
		try {
			// désérialisation du ficher XML
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(fileName);
			Document jdomDoc = (Document) builder.build(xmlFile);

			// récupération de l'élément racine et tranfert dans une liste d'éléments
			Element root = jdomDoc.getRootElement();
			List<Element> listOfCompte = root.getChildren("CompteBancaire");

			// transfert des comptes bancaires dans une liste

			for (Element compteElement : listOfCompte) {
				CompteBancaire compte = new CompteBancaire();
				compte.setNumCompte(Integer.parseInt(compteElement.getChildText("numCompte")));
				compte.setNomPropriétaire(compteElement.getChildText("nomPropriétaire"));
				compte.setSolde(Double.parseDouble(compteElement.getChildText("solde")));
				compte.setDateCreation(fromStringToLocalDate(compteElement.getChildText("dateCreation")));
				compte.setTypeCompte(compteElement.getChildText("typeCompte"));
				compteList.add(compte);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return compteList;
	}

	private static LocalDate fromStringToLocalDate(String stringDate) {
		char[] charList = stringDate.toCharArray();
		int year = Integer.parseInt("" + charList[0] + charList[1] + charList[2] + charList[3]);
		int month = Integer.parseInt("" + charList[5] + charList[6]);
		int day = Integer.parseInt("" + charList[8] + charList[9]);
		return LocalDate.of(year, month, day);
	}

	private static int getChoiceList(Scanner scanner) {
		int choice = GestionScanner.getInt(scanner);
		if (choice == 1 || choice == 2 || choice == 3) {
			return choice;
		} else {
			System.err.println("Erreur de saisie. Veuillez saisir un choix correct.");
			return getChoiceList(scanner);
		}
	}

}
