package manager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class IdAscSort implements Comparator<Student> {
	@Override
	public int compare(Student s1, Student s2) {
		return s1.getId().compareTo(s2.getId());
	}
}

class IdDescSort implements Comparator<Student> {
	@Override
	public int compare(Student s1, Student s2) {
		return s2.getId().compareTo(s1.getId());
	}
}

class ScoreAscSort implements Comparator<Student> {
	@Override
	public int compare(Student s1, Student s2) {
		return s1.getScore().compareTo(s2.getScore());
	}
}

class ScoreDescSort implements Comparator<Student> {
	@Override
	public int compare(Student s1, Student s2) {
		return s2.getScore().compareTo(s1.getScore());
	}
}

public class StudentManager {
	private List<Student> listStudent = new ArrayList<Student>();
	public static final String FILE_LIST_STUDENT = "listStudent";
	public static final String HEADER_FILE_CSV = "ID,Name,Score,Image,Address,Note\n";

	// constructor
	public StudentManager() throws Exception {
		// Read list student from file
		this.readFile();
	}

	public void addStudent(Student s) {
		listStudent.add(s);
		System.out.println("\n========\tADD STUDENT SUCCESSED\t============");
	}

	public void clearAllStudent() {
		listStudent.clear();
	}

	public void updateStudent(int index) {
		// Check condition
		if (index >= listStudent.size()) {
			System.out.println("Out of length list users. Please chooose number lower.");
			return;
		}
		listStudent.get(index).updateStudent();
		System.out.println("\n========\tUPDATE STUDENT SUCCESSED\t============");

	}

	public void deleteStudent(int index) {
		if (index >= listStudent.size()) {
			System.out.println("Out of length list users. Please chooose number lower.");
			return;
		}
		listStudent.remove(index);
		System.out.println("\n========\tDELETE STUDENT " + index + " SUCCESSED\t============");

	}

	// View Student by Score and ID
	// 1: By ID ASC
	// 2: By ID DESC
	// 3: By Score ASC
	// 4: By Score DESC
	public void viewStudent(int mode) {
		// Check mode
		if (mode == 1) {
			System.out.println("\n--------LIST STUDENT BY ID ASC---------------");
			Collections.sort(listStudent, new IdAscSort());
		} else if (mode == 2) {
			System.out.println("\n--------LIST STUDENT BY ID DESC---------------");
			Collections.sort(listStudent, new IdDescSort());
		} else if (mode == 3) {
			System.out.println("\n--------LIST STUDENT BY SCORE ASC---------------");
			Collections.sort(listStudent, new ScoreAscSort());
		} else if (mode == 4) {
			System.out.println("\n--------LIST STUDENT BY SCORE DESC---------------");
			Collections.sort(listStudent, new ScoreDescSort());
		}
		this.printStudent();
	}

	private void printStudent() {
		System.out.println("\nList of  Student( " + listStudent.size() + " ): \n");
		int i = 0;
		if (listStudent.isEmpty() == true) {
			System.out.println("List students is empty");
		}
		for (Student s : listStudent) {
			System.out.println("STT: " + i + "\t" + s);
			i++;
		}
	}

	public void importFileCSV(String file) throws Exception {
		listStudent.clear();
		String id, name, image, address, note;
		Float score = 4f;
		String temp;
		Scanner scanner = new Scanner(new File(file));
		scanner.useDelimiter(",");
		scanner.next();
		scanner.next();
		scanner.next();
		scanner.next();
		scanner.next();
		temp = scanner.next();
		String[] part = temp.split("\n");
		while (scanner.hasNext()) {
			id = part[1];
			name = scanner.next();
			temp = scanner.next();
			score = Float.parseFloat(temp);
			image = scanner.next();
			address = scanner.next();
			temp = scanner.next();
			part = temp.split("\n");
			note = part[0];
			listStudent.add(new Student(id, name, score, image, address, note));
			// System.out.print(scanner.next() + "|");
		}
		scanner.close();
		System.out.println("\n========\tIMPORT FILE SUCCESSED\t============");
	}

	public void exportFileCSV(String file) {
		try {
			PrintWriter printWriter = new PrintWriter(new File(file));
			StringBuilder stringBuilder = new StringBuilder();

			stringBuilder.append(HEADER_FILE_CSV);
			for (Student student : listStudent) {
				stringBuilder.append(student.writeCSV());
			}
			// System.out.println(stringBuilder.toString());
			printWriter.write(stringBuilder.toString());
			System.out.println("\n========\tEXPORT FILE SUCCESSED\t============");
			printWriter.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	public void readFile() throws Exception {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(FILE_LIST_STUDENT)));
			listStudent.clear();
			try {
				while (true) {
					Student s = (Student) in.readObject();
					listStudent.add(s);
					// System.out.println(s);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		} finally {
			in.close();
		}
	}

	public void saveFile() throws IOException {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(FILE_LIST_STUDENT)));
			for (Student s : listStudent) {
				out.writeObject(s);
				// System.out.println("Save File: " + s);
			}
		} finally {
			// TODO: handle finally clause
			System.out.println("File saved..");
			out.close();
		}
	}

}