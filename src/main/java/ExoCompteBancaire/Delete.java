package ExoCompteBancaire;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Delete {

	public static void deleteBankAccount(Scanner scanner) {
		try {
			final String fileName = "target/CompteBancaires.xml";
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(fileName);
			Document jdomDoc = (Document) builder.build(xmlFile);
			Element root = jdomDoc.getRootElement();
			List<Element> listOfCompte = root.getChildren("BankAccount");

			// sélection du compte à suprimer
			System.out.println("Veuillez saisir le numéro du compte à supprimer : ");
			int numCompte = GestionScanner.getInt(scanner);
			int i = 0;
			for (Element compte : listOfCompte) {
				if (Integer.parseInt(compte.getChildText("NumCompte")) == numCompte) {
					root.removeContent(compte);
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
			xmlOutput.output(jdomDoc, new FileWriter("target/CompteBancaires.xml"));
			System.out.println("File Saved!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
