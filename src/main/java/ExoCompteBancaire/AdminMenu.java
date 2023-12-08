package ExoCompteBancaire;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public abstract class AdminMenu {

	public static void DoAdminMenu(Scanner scanner) {
		showAdminMenu();
		int choice = getChoice(scanner);
		doChoice(scanner, choice);

	}

	private static void doChoice(Scanner scanner, int choice) {
		switch (choice) {
		case 0:
			break;
		case 1:
			System.out.println("Etes-vous sûr de vouloir créer un nouveau fichier XML? Oui / Non ?");
			System.out.println(
					"ATTENTION : Cette action va supprimer le contenu du fichier XML actuel et ne peut être annulée.");
			if (GestionScanner.getYN(scanner)) {
				XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
				Document doc = new Document();
				doc.setRootElement(new Element("CompteBancaires"));
				try {
					xmlOutput.output(doc, new FileWriter("target/CompteBancaires.xml"));
				} catch (IOException io) {
					System.out.println(io.getMessage());
				}
				System.out.println("Fichier XML créé");
			}
			showAdminMenu();
			choice = getChoice(scanner);
			doChoice(scanner, choice);
		case 2:
			Udpate.addTransaction(scanner);
			showAdminMenu();
			choice = getChoice(scanner);
			doChoice(scanner, choice);
		case 3:
			Udpate.addAccountAttribute(scanner);
			showAdminMenu();
			choice = getChoice(scanner);
			doChoice(scanner, choice);
		}

	}

	public static void showAdminMenu() {
		System.out.println("Que souhaitez-vous faire ? ");
		System.out.println("0. Quitter");
		System.out.println("1. Creer le fichier XML");
		System.out.println("2. Ajouter une transaction");
		System.out.println("3. Ajouter un attribut de compte");
	}

	private static int getChoice(Scanner scanner) {
		int choice = GestionScanner.getInt(scanner);
		scanner.nextLine();
		if (choice == 0 || choice == 1 || choice == 2 || choice == 3) {
			return choice;
		} else {
			System.err.println("Erreur de saisie. Veuillez saisir un choix correct.");
			return getChoice(scanner);
		}
	}

}
