package manager;

import java.io.Serializable;
import java.util.Scanner;

public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String name;

	private String address;

	private String image;

	private String note;
	private Float score;

	public Student() {

	}

	public Student(String id, String name, Float score, String image, String address, String note) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
		this.address = address;
		this.image = image;
		this.note = note;
		this.score = score;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ID: " + id + "\tName: " + name + "\tScore:" + score.toString() + "\tImage: " + image + "\tAddress: "
				+ address + "\tNote: " + note;
	}

	public String writeCSV() {
		return id + "," + name + "," + score + "," + image + "," + address + "," + note + "\n";
	}

	public Student inputStudent() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.print("Input ID: ");
		this.id = scanner.nextLine();
		System.out.print("\nInput Name: ");
		this.name = scanner.nextLine();
		System.out.print("\nInput Score: ");
		this.score = Float.parseFloat(scanner.nextLine());
		System.out.print("\nInput Link Image: ");
		this.image = scanner.nextLine();
		System.out.print("\nInput Address: ");
		this.address = scanner.nextLine();
		System.out.print("\nInput Note: ");
		this.note = scanner.nextLine();
		// scanner.close();
		return this;
	}

	public Student updateStudent() {
		System.out.println(this);
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.print("Input ID: ");
		this.id = scanner.nextLine();
		System.out.print("\nInput Name: ");
		this.name = scanner.nextLine();
		System.out.print("\nInput Score: ");
		this.score = Float.parseFloat(scanner.nextLine());
		System.out.print("\nInput Link Image: ");
		this.image = scanner.nextLine();
		System.out.print("\nInput Address: ");
		this.address = scanner.nextLine();
		System.out.print("\nInput Note: ");
		this.note = scanner.nextLine();
		return this;
	}

	public String getId() {
		return this.id;
	}

	public Float getScore() {
		return this.score;
	}

}