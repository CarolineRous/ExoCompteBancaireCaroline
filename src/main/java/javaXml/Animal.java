package javaXml;

public class Animal {
	private String name;
	private String species;
	private int age;
	private double weight;

	public Animal(String name, String species, int age, double weight) {
		super();
		this.name = name;
		this.species = species;
		this.age = age;
		this.weight = weight;
	}

	public Animal() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getSpecies() {
		return species;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Animal [name=" + name + ", species=" + species + ", age=" + age + ", weight=" + weight + "]";
	}

}
