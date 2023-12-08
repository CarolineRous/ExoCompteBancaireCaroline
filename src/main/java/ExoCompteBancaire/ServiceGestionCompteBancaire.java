package ExoCompteBancaire;

import java.util.Scanner;

public class ServiceGestionCompteBancaire {

//	Runtime runtime = Runtime.getRuntime();
//	System.out.println("Used Memory:" + (runtime.totalMemory() - runtime.freeMemory()) / 1024 + " Ko")

	public static void main(String[] args) {

		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Bienvenue dans le service de gestion des comptes bancaires");
			showMainMenu();
			int choice = getChoice(scanner);
			doChoice(scanner, choice);
		}
	}

	private static void doChoice(Scanner scanner, int choice) {
		switch (choice) {
		case -1:
			AdminMenu.DoAdminMenu(scanner);
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
		System.out.println("Que souhaitez-vous faire ? ");
		System.out.println("-1. Menu administrateur");
		System.out.println("0. Quitter");
		System.out.println("1. Créer un compte bancaire");
		System.out.println("2. Afficher un compte bancaire");
		System.out.println("3. Lister les comptes bancaires");
		System.out.println("4. Modifier un compte bancaire");
		System.out.println("5. Supprimer un compte bancaire");
	}

	private static int getChoice(Scanner scanner) {
		int choice = GestionScanner.getInt(scanner);
		scanner.nextLine();
		if (choice == -1 || choice == 0 || choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5) {
			return choice;
		} else {
			System.err.println("Erreur de saisie. Veuillez saisir un choix correct.");
			return getChoice(scanner);
		}
	}

}
