package ExoCompteBancaire;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class ReadList {

	public static void main(String[] args) {
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
				compte.setNumCompte(Integer.parseInt(compteElement.getChildText("NumCompte")));
				compte.setNomPropriétaire(compteElement.getChildText("NomPropriétaire"));
				compte.setSolde(Double.parseDouble(compteElement.getChildText("Solde")));
				compte.setDateCreation(fromStringToLocalDate(compteElement.getChildText("DateCreation")));
				compte.setTypeCompte(compteElement.getChildText("TypeCompte"));

				compteList.add(compte);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// affichage de la liste des comptes bancaires
		try (Scanner scanner = new Scanner(System.in)) {
			choixListeCompteBancaire(scanner, compteList);
		}
	}

	private static void choixListeCompteBancaire(Scanner scanner, List<CompteBancaire> compteList) {
		System.out.println("Veuillez choisir parmi les options suivantes : ");
		System.out.println("Liste de tous les comptes bancaires : tapez 1 .");
		System.out.println("Liste des comptes bancaires courants : tapez 2.");
		System.out.println("Liste des comptes bancaires épargnes : tapez 3.");
		int choice = getChoice(scanner);
		switch choice {
		case 1: {for (CompteBancaire compte : compteList) {
			System.out.println(compte);
		}}
		}
		for (CompteBancaire compte : compteList) {
			System.out.println(compte);
		}
		
	}

	private static int getChoice(Scanner scanner) {
		int choice = getInt(scanner);
		if (choice == 1 || choice == 2 || choice == 3) {
			return scanner.nextInt();
		} else {
			System.err.println("Erreur de saisie. Veuillez saisr un choix correct.");
			return getChoice(scanner);
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
}
