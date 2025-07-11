import java.util.ArrayList;
import java.util.Scanner;

// Class for Book
class Book {
    int id;
    String title;
    String author;
    boolean isIssued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public void display() {
        System.out.println(id + ". " + title + " by " + author + " - " + (isIssued ? "Issued" : "Available"));
    }
}

// Main class
public class DigitalLibrary {

    static ArrayList<Book> books = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // Admin menu
    public static void adminMenu() {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. View All Books");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    books.add(new Book(id, title, author));
                    System.out.println("Book added successfully!");
                    break;

                case 2:
                    System.out.print("Enter Book ID to delete: ");
                    int deleteId = sc.nextInt();
                    books.removeIf(book -> book.id == deleteId);
                    System.out.println("Book deleted successfully!");
                    break;

                case 3:
                    viewBooks();
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // User menu
    public static void userMenu() {
        while (true) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. View Available Books");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewBooks();
                    break;

                case 2:
                    System.out.print("Enter Book ID to issue: ");
                    int issueId = sc.nextInt();
                    boolean issued = false;
                    for (Book b : books) {
                        if (b.id == issueId && !b.isIssued) {
                            b.isIssued = true;
                            System.out.println("Book issued successfully!");
                            issued = true;
                            break;
                        }
                    }
                    if (!issued) System.out.println("Book not found or already issued.");
                    break;

                case 3:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();
                    boolean returned = false;
                    for (Book b : books) {
                        if (b.id == returnId && b.isIssued) {
                            b.isIssued = false;
                            System.out.println("Book returned successfully!");
                            returned = true;
                            break;
                        }
                    }
                    if (!returned) System.out.println("Invalid return request.");
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // View all books
    public static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        for (Book b : books) {
            b.display();
        }
    }

    // Main method
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Digital Library Management ===");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Admin Password: ");
                    String pass = sc.next();
                    if (pass.equals("admin123")) {
                        adminMenu();
                    } else {
                        System.out.println("Incorrect password!");
                    }
                    break;

                case 2:
                    userMenu();
                    break;

                case 3:
                    System.out.println("Thank you for using the system. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}