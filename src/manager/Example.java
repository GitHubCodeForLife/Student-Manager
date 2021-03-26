package manager;

import java.io.FileReader;
import java.io.Reader;
import java.util.Scanner;

public class Example {
	public static void main(String[] args) throws Exception {
		StudentManager studentManager = new StudentManager();
		int index;
		String file;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (true) {
			// Print menu
			try {
				Reader reader = new FileReader("menu.txt");
				int data = reader.read();
				while (data != -1) {
					System.out.print((char) data);
					data = reader.read();
				}
				reader.close();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

			// Choose method
			int n = scanner.nextInt();

			// Switch n
			switch (n) {
			case 1:
				// 1. Add Students
				System.out.println("\n---------------1: ADD STUDENT ---------------");
				Student s = new Student();
				s.inputStudent();
				studentManager.addStudent(s);
				break;
			case 2:
				// 2. Update Students
				System.out.println("\n---------------2: UPDATE STUDENT ---------------");
				studentManager.viewStudent(0);
				System.out.print("\nInput position that you want to update (-1 to exit delete mode): ");
				index = scanner.nextInt();
				if (index == -1)
					break;
				studentManager.updateStudent(index);
				break;
			case 3:
				// 3. Delete a Students
				System.out.println("\n---------------3: DELETE STUDENT ---------------");
				studentManager.viewStudent(0);
				System.out.print("\nInput position that you want to delete (-1 to exit delete mode): ");
				index = scanner.nextInt();
				if (index == -1)
					break;
				studentManager.deleteStudent(index);
				break;
			case 4:
				// 4. View list students
				System.out.println("\n---------------4: VIEW STUDENT ---------------");
				System.out.println("Choose mode view: ");
				System.out.println("Filter by ID: ");
				System.out.println("\t1: ID ASC ");
				System.out.println("\t2: ID DESC");
				System.out.println("Filter by Score:");
				System.out.println("\t3:  SCORE ASC");
				System.out.println("\t4:  SCORE DESC");
				System.out.print("Your Choose: ");
				int mode = scanner.nextInt();
				if (mode < 1 || mode > 4) {
					System.out.println("\nWARNING !!! you should choose from 1 to 4");
					break;
				}
				studentManager.viewStudent(mode);
				break;
			case 5:
				// 5. Import file text
				System.out.println("\n---------------5: IMPORT FILE CSV ---------------");
				System.out.print("\nInput link file that you want to import: ");
				file = scanner.nextLine();
				file = scanner.nextLine();
				// System.out.println("Name File " + file);
				studentManager.importFileCSV(file);
				break;
			case 6:
				// 6. Export file text
				System.out.println("\n---------------6: EXPORT FILE CSV ---------------");
				System.out.print("\nInput link file that you want to exports: ");
				file = scanner.nextLine();
				file = scanner.nextLine();
				// System.out.println("\nName File " + file);
				studentManager.exportFileCSV(file);
				break;
			default:
				// 7. Exit
				studentManager.saveFile();
				System.out.print("Goodbye see you next time. Tks you.");
				return;
			}
		}
	}
}
