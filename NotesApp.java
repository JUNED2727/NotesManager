package NotesReadWrite;

import java.io.*;
import java.util.Scanner;

public class NotesApp {

    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n===== NOTES APP =====");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addNote(scanner);
                    break;

                case 2:
                    viewNotes();
                    break;

                case 3:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Write notes using FileWriter (append mode)
    private static void addNote(Scanner scanner) {
        try {
            System.out.print("Enter your note: ");
            String note = scanner.nextLine();

            FileWriter writer = new FileWriter(FILE_NAME, true); // append = true
            writer.write(note + "\n");
            writer.close();

            System.out.println("Note added successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Read notes using FileReader + BufferedReader
    private static void viewNotes() {
        try {
            File file = new File(FILE_NAME);

            if (!file.exists()) {
                System.out.println("No notes found.");
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            System.out.println("\n----- Saved Notes -----");
            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
