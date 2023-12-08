package ExoCompteBancaire;

import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Delete {

	public static void deleteBankAccount(Scanner scanner) {
		try {
			List<BankAccount> compteList = ReadList.read(scanner);
			Document jdomdoc = new Document();
			jdomdoc.setRootElement(new Element("CompteBancaires"));

			// sélection du compte à suprimer
			System.out.println("Veuillez saisir le numéro du compte à supprimer : ");
			int numCompte = GestionScanner.getInt(scanner);
			int i = 0;
			for (BankAccount compte : compteList) {
				if (compte.getNumCompte() != numCompte) {
					jdomdoc.getRootElement().addContent(Create.createBankAccountXMLElement(compte));
					i = 1;
				}
			}
			if (i == 0) {
				System.out.println("Le compte " + numCompte + " n'existe pas.");
			} else if (i == 1) {
				System.out.println("Le compte " + numCompte + " a été supprimé.");
			}

			// sérialisation du fichier XML
			XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
			xmlOutput.output(jdomdoc, new FileWriter("CompteBancaires.xml"));
			System.out.println("File Saved!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
