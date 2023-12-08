package ExoCompteBancaire;

import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
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
			xmlOutput.output(jdomdoc, new FileWriter("CompteBancaires.xml"));
			System.out.println("File Saved!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addAccountAttribute(Scanner scanner) {
		try {
			List<BankAccount> compteList = ReadList.read(scanner);
			Document jdomdoc = new Document();
			jdomdoc.setRootElement(new Element("CompteBancaires"));

			// Ajout de l'attribut typeBanque dans l'élément CaompteBancaire

			for (BankAccount compte : compteList) {
				Element temp = Create.createBankAccountXMLElement(compte);
				temp.setAttribute(new Attribute("typeBanque", "Physique"));
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
