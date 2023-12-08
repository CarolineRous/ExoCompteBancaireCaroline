package ExoCompteBancaire;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Udpate {

	public static void updateBankAccount(Scanner scanner) {
		try {
			List<BankAccount> compteList = ReadList.read(scanner);
			Document jdomdoc = new Document();
			jdomdoc.setRootElement(new Element("CompteBancaires"));

			// sélection du compte à modifier
			System.out.println("Veuillez saisir le numéro du compte à modifer : ");
			int numCompte = GestionScanner.getInt(scanner);
			int i = 0;
			for (BankAccount compte : compteList) {
				if (compte.getNumCompte() == numCompte) {
					System.out.println(compte);
					BankAccount newCompte = Create.infoEntriesNext(scanner, numCompte);
					jdomdoc.getRootElement().addContent(Create.createBankAccountXMLElement(newCompte));
					System.out.println("Le compte " + newCompte.getNumCompte() + " a été modifé.");
					System.out.println(newCompte);
					i = 1;
				}
			}
			if (i == 0) {
				System.out.println("Le compte " + numCompte + " n'existe pas.");
				for (BankAccount compte : compteList) {
					jdomdoc.getRootElement().addContent(Create.createBankAccountXMLElement(compte));
				}
			}

			// sérialisation du fichier XML
			XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
			xmlOutput.output(jdomdoc, new FileWriter("target/CompteBancaires.xml"));
			System.out.println("File Saved!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addAccountAttribute(Scanner scanner) {
		try {
			final String fileName = "target/CompteBancaires.xml";
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(fileName);
			Document jdomDoc = (Document) builder.build(xmlFile);
			Element root = jdomDoc.getRootElement();
			List<Element> listOfCompte = root.getChildren("BankAccount");

			// Ajout de l'attribut typeBanque dans l'élément CaompteBancaire
			System.out.println("Veuillez saisir le numéro du compte à modifier : ");
			int numCompte = GestionScanner.getInt(scanner);
			scanner.nextLine();
			int i = 0;
			for (Element compte : listOfCompte) {
				if (numCompte == Integer.parseInt(compte.getChildText("numCompte"))) {
					System.out.println("Veuilez saisir le nom de l'attribut : ");
					String nom = GestionScanner.getString(scanner);
					System.out.println("Veuillez saisir la valeur de l'attribut : ");
					String valeur = GestionScanner.getString(scanner);
					compte.setAttribute(new Attribute(nom, valeur));
					i = 1;
				}
			}
			if (i == 0) {
				System.out.println("Le compte " + numCompte + " n'existe pas.");
			} else if (i == 1) {
				System.out.println("L'attribut du compte " + numCompte + " a été modifé.");
			}

			// sérialisation du fichier XML
			XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
			xmlOutput.output(jdomDoc, new FileWriter("target/CompteBancaires.xml"));
			System.out.println("File Saved!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void addTransaction(Scanner scanner) {
		// TODO Auto-generated method stub

	}
}
