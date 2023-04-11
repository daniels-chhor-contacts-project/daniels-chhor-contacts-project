import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactsTest {
    private static ArrayList<String> contacts = new ArrayList<>();

    public static void main(String[] args) {
        loadContacts();

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\nContacts Manager");
            System.out.println("-----------------");
            System.out.println("1. Show all contacts");
            System.out.println("2. Add a new contact");
            System.out.println("3. Search a contact by name");
            System.out.println("4. Delete an existing contact");
            System.out.println("5. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    showContacts();
                    break;
                case 2:
                    addContact(input);
                    break;
                case 3:
                    searchContact(input);
                    break;
                case 4:
                    deleteContact(input);
                    break;
                case 5:
                    saveContacts();
                    System.out.println("\nThank you for using Contacts Manager!");
                    System.exit(0);
                default:
                    System.out.println("\nInvalid choice! Please enter a valid choice.");
                    break;
            }
        }
    }

    private static void showContacts() {
        if (contacts.isEmpty()) {
            System.out.println("\nNo contacts found!");
            return;
        }

        System.out.println("\nContacts:");
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println((i + 1) + ". " + contacts.get(i));
        }
    }

    private static void addContact(Scanner input) {
        System.out.print("\nEnter the first name of the new contact: ");
        String firstName = input.next();
        System.out.print("\nEnter the last name of the new contact: ");
        String lastName = input.next();
        System.out.print("\nEnter the phone number of the new contact: ");
        String phoneNumber = input.next();
        String contact = firstName + " " + lastName + " | " + phoneNumber;
        contacts.add(contact);
        System.out.println("\nContact added successfully!");
    }

    private static void searchContact(Scanner input) {
        System.out.print("\nEnter the name of the contact to search: ");
        String searchName = input.next();

        for (String name : contacts) {
            if (name.equals(searchName)) {
                System.out.println("\nContact found: " + name);
                return;
            }
        }

        System.out.println("\nContact not found!");
    }

    private static void deleteContact(Scanner input) {
        if (contacts.isEmpty()) {
            System.out.println("\nNo contacts found to delete!");
            return;
        }

        System.out.print("\nEnter the index of the contact to delete: ");
        int index = input.nextInt();

        if (index < 1 || index > contacts.size()) {
            System.out.println("\nInvalid index! Please enter a valid index.");
            return;
        }

        String deletedContact = contacts.remove(index - 1);
        System.out.println("\nContact deleted successfully: " + deletedContact);
    }

    private static void loadContacts() {
        try {
            File file = new File("contact.txt");
            if (!file.exists()) {
                return;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                contacts.add(line);
            }
            scanner.close();
            System.out.println("Contacts loaded successfully from file!");
        } catch (FileNotFoundException e) {
            System.out.println("Error loading contacts from file: " + e.getMessage());
        }
    }

    private static void saveContacts() {
        try {
            FileWriter writer = new FileWriter("contact.txt");
            for (String contact : contacts) {
                writer.write(contact + "\n");
            }
            writer.close();
            System.out.println("\nContacts saved successfully to file!");
        } catch (IOException e) {
            System.out.println("\nError saving contacts to file: " + e.getMessage());
        }
    }
}
