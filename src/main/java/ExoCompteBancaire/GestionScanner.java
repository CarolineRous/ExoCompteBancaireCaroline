package ExoCompteBancaire;

import java.time.LocalDate;
import java.util.Scanner;

public abstract class GestionScanner {

	static String getString(Scanner scanner) {
		try {
			return scanner.nextLine();
		} catch (Exception e) {
			scanner.nextLine();
			System.err.println("Erreur de saisie. Veuillez réessayer.");
			return getString(scanner);
		}
	}

	static int getInt(Scanner scanner) {
		try {
			return scanner.nextInt();
		} catch (Exception e) {
			scanner.nextLine();
			System.err.println("Erreur de saisie. Veuillez réessayer.");
			return getInt(scanner);
		}
	}

	static LocalDate getDate(Scanner clavier) {
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

	static Double getDouble(Scanner scanner) {
		try {
			return scanner.nextDouble();
		} catch (Exception e) {
			scanner.nextLine();
			System.err.println("Erreur de saisie. Veuillez réessayer.");
			return getDouble(scanner);
		}
	}

	public static boolean getYN(Scanner scanner) {
		String answer = getString(scanner);
		if (answer.equalsIgnoreCase("oui")) {
			return true;
		} else if (answer.equalsIgnoreCase("non")) {
			return false;
		} else {
			System.err.println("Erreur de saisie. Veuillez réessayer.");
			return getYN(scanner);
		}
	}
}
