import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Contacts {
    private static ArrayList<String> contacts = new ArrayList<>();

    public static void main(String[] args) {
        loadContacts();

        Scanner input = new Scanner(System.in);
//while loop for mennu
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
//switch case for each choice on menu
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
//method to show contacts
    private static void showContacts() {
//        if no contacts on file
        if (contacts.isEmpty()) {
            System.out.println("\nNo contacts found!");
            return;
        }
//for loop to loop through contacts if on file * ADDED BONUS
        System.out.printf("\n%-20s | %-12s\n", "Name (First, Last)", "Phone Number");
        System.out.println("-------------------------------------");
        for (int i = 0; i < contacts.size(); i++) {
            String[] contact = contacts.get(i).split("\\|");
            String name = contact[0].trim();
            String phone = contact[1].trim();
            System.out.printf("%-20s | %-12s\n", name, phone);
        }
    }
//adding contact method
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
//method to search for contacts
    private static void searchContact(Scanner input) {
        System.out.print("\nEnter the name of the contact to search: ");
        String searchName = input.next().toLowerCase();
//splitting first last
        for (String contact : contacts) {
            String[] nameParts = contact.split(" ");
//            to make not case sensitive
            String firstName = nameParts[0].toLowerCase();
            String lastName = nameParts[1].toLowerCase();
            if (firstName.equals(searchName) || lastName.equals(searchName)) {
                System.out.println("\nContact found: " + contact);
                return;
            }
        }

        System.out.println("\nContact not found!");
    }
//deleting contact method
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
//loading contacts from file path
    private static void loadContacts() {
        try {
            Path filePath = Paths.get("contacts.txt");
            if (!Files.exists(filePath)) {
                return;
            }

            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                contacts.add(line);
            }
            System.out.println("Contacts loaded successfully from file!");

        } catch (IOException e) {
            System.out.println("Error loading contacts from file: " + e.getMessage());
        }
    }
//saving contacts to file path
    private static void saveContacts() {
        try {
            FileWriter writer = new FileWriter("contacts.txt");
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
