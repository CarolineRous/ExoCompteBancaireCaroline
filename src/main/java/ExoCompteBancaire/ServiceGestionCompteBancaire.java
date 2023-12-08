package ExoCompteBancaire;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class ServiceGestionCompteBancaire {

	public static void main(String[] args) {

		try (Scanner scanner = new Scanner(System.in)) {
			showMainMenu();
			int choice = getChoice(scanner);
			doChoice(scanner, choice);
		}
	}

	private static void doChoice(Scanner scanner, int choice) {
		switch (choice) {
		case -1:
			System.out.println("Etes-vous sûr de vouloir créer un nouveau fichier XML?");
			System.out.println("Cette action va supprimer le contenu du fichier XML actuel et ne peut être annulée.");
			System.out.println("1. Oui");
			System.out.println("2. Non");
			String answer = GestionScanner.getYN(scanner);
			if (answer.equals("1")) {
				XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
				Document doc = new Document();
				doc.setRootElement(new Element("CompteBancaires"));
				try {
					xmlOutput.output(doc, new FileWriter("CompteBancaires.xml"));
				} catch (IOException io) {
					System.out.println(io.getMessage());
				}
				System.out.println("Fichier XML créé");
			}
			showMainMenu();
			choice = getChoice(scanner);
			doChoice(scanner, choice);
		case 0:
			break;
		case 1:
			Create.createBankAccount(scanner);
			showMainMenu();
			choice = getChoice(scanner);
			doChoice(scanner, choice);
		case 2:
			ReadList.afficherCompteBancaire(scanner);
			showMainMenu();
			choice = getChoice(scanner);
			doChoice(scanner, choice);
		case 3:
			ReadList.selectListAccountType(scanner);
			showMainMenu();
			choice = getChoice(scanner);
			doChoice(scanner, choice);
		case 4:
			Udpate.updateBankAccount(scanner);
			showMainMenu();
			choice = getChoice(scanner);
			doChoice(scanner, choice);
		case 5:
			Delete.deleteBankAccount(scanner);
			showMainMenu();
			choice = getChoice(scanner);
			doChoice(scanner, choice);
		}

	}

	private static void showMainMenu() {
		System.out.println("Bienvenue dans le service de gestion des comptes bancaires");
		System.out.println("Que souhaitez-vous faire ? ");
		System.out.println("-1. Création du fichier XML");
		System.out.println("0. Quitter");
		System.out.println("1. Créer un compte bancaire");
		System.out.println("2. Afficher un compte bancaire");
		System.out.println("3. Lister les comptes bancaires");
		System.out.println("4. Modifier un compte bancaire");
		System.out.println("5. Supprimer un compte bancaire");
	}

	private static int getChoice(Scanner scanner) {
		int choice = GestionScanner.getInt(scanner);
		if (choice == -1 || choice == 0 || choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5) {
			return choice;
		} else {
			System.err.println("Erreur de saisie. Veuillez saisir un choix correct.");
			return getChoice(scanner);
		}
	}

}
