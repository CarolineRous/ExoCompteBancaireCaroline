package javaXml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class Read {

	public static void main(String[] args) {
		final String fileName = "animals.xml";
		try {
			// désérialisation du ficher XML
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(fileName);
			Document jdomDoc = (Document) builder.build(xmlFile);

			// récupération de l'élément racine et tranfert dans une liste d'éléments
			Element root = jdomDoc.getRootElement();
			List<Element> listOfAnimals = root.getChildren("Animal");

			// transfert des animaux dans une liste
			List<Animal> animalList = new ArrayList<Animal>();
			for (Element animalElement : listOfAnimals) {
				Animal animal = new Animal();
				animal.setName(animalElement.getChildText("Name"));
				animal.setSpecies(animalElement.getChildText("Species"));
				animal.setAge(Integer.parseInt(animalElement.getChildText("Age")));
				animal.setWeight(Double.parseDouble(animalElement.getChildText("Weight")));
				animalList.add(animal);
			}

			// affichage de la liste d'animaux
			for (Animal animal : animalList) {
				System.out.println(animal);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
