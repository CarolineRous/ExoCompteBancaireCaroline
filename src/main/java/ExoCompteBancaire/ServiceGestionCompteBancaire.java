package ExoCompteBancaire;

import java.util.Scanner;

public class ServiceGestionCompteBancaire {

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Bienvenue dans le service de gestion des comptes bancaires");
			System.out.println("Que souhaitez-vous faire ? ");
			System.out.println("0. Quitter");
			System.out.println("1. Créer un compte bancaire");
			System.out.println("2. Afficher un compte bancaire");
			System.out.println("3. Lister les comptes bancaires");
			System.out.println("4. Supprimer un compte bancaire");
			System.out.println("5. Modifier un compte bancaire");
			int choice = getChoice(scanner);
			switch (choice) {
			case 0:
				break;
			case 1:
				CreateWriteFromKeyboard.createCompteBancaire(scanner);
				break;
			case 2:
				ReadList.afficherCompteBancaire(scanner);
				break;
			case 3:
				ReadList.choixListeCompteBancaire(scanner);
				break;
			case 4:
				Delete.supprimerCompteBancaire(scanner);
				break;
			case 5:
				Update.modifierCompteBancaire(scanner);
				break;
			}
		}
	}

	private static int getChoice(Scanner scanner) {
		int choice = GestionScanner.getInt(scanner);
		if (choice == 0 || choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5) {
			return choice;
		} else {
			System.err.println("Erreur de saisie. Veuillez saisir un choix correct.");
			return getChoice(scanner);
		}
	}

}
