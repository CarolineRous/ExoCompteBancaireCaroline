package javaXml;

import java.io.FileWriter;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class CreateWrite {

	public static void main(String[] args) {
		try {
			Document doc = new Document();
			doc.setRootElement(new Element("Animals"));

			doc.getRootElement().addContent(createAnimalXMLElement(new Animal("Kira", "Chien", 4, 40.)));
			doc.getRootElement().addContent(createAnimalXMLElement(new Animal("Mew", "Chat", 4, 3.9)));

			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("animals.xml"));
			System.out.println("File Saved!");
		} catch (IOException io) {
			System.out.println(io.getMessage());
		}
	}

	private static Element createAnimalXMLElement(Animal animal) {
		Element animalElement = new Element("Animal");
		animalElement.addContent(new Element("Name").setText(animal.getName()));
		animalElement.addContent(new Element("Species").setText(animal.getSpecies()));
		animalElement.addContent(new Element("Age").setText("" + animal.getAge()));
		animalElement.addContent(new Element("Weight").setText("" + animal.getWeight()));
		return animalElement;
	}

}
