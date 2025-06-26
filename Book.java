import java.util.*;

class Book {
    int id;
    String title;
    boolean isIssued;

    Book(int id, String title) {
        this.id = id;
        this.title = title;
        this.isIssued = false;
    }

    public String toString() {
        return "Book ID: " + id + ", Title: " + title + ", Issued: " + isIssued;
    }
}

class User {
    int id;
    String name;

    User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return "User ID: " + id + ", Name: " + name;
    }
}

public class main{
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList<User> users = new ArrayList<>();
    static Map<Integer, Integer> issued = new HashMap<>(); // bookId -> userId

    public static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();
        books.add(new Book(id, title));
        System.out.println("Book added.");
    }

    public static void addUser() {
        System.out.print("Enter User ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter User Name: ");
        String name = sc.nextLine();
        users.add(new User(id, name));
        System.out.println("User added.");
    }

    public static void viewBooks() {
        if (books.isEmpty()) System.out.println("No books available.");
        else books.forEach(System.out::println);
    }

    public static void viewUsers() {
        if (users.isEmpty()) System.out.println("No users available.");
        else users.forEach(System.out::println);
    }

    public static void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        int bookId = sc.nextInt();
        System.out.print("Enter User ID: ");
        int userId = sc.nextInt();

        Book foundBook = null;
        for (Book b : books) {
            if (b.id == bookId && !b.isIssued) {
                foundBook = b;
                break;
            }
        }

        if (foundBook != null) {
            foundBook.isIssued = true;
            issued.put(bookId, userId);
            System.out.println("Book issued to user.");
        } else {
            System.out.println("Book not found or already issued.");
        }
    }

    public static void viewIssuedBooks() {
        if (issued.isEmpty()) {
            System.out.println("No books issued.");
            return;
        }

        for (Map.Entry<Integer, Integer> entry : issued.entrySet()) {
            int bookId = entry.getKey();
            int userId = entry.getValue();

            Book book = books.stream().filter(b -> b.id == bookId).findFirst().orElse(null);
            User user = users.stream().filter(u -> u.id == userId).findFirst().orElse(null);

            if (book != null && user != null) {
                System.out.println(book.title + " issued to " + user.name);
            }
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Book Management System ---");
            System.out.println("1. Add Book\n2. View Books\n3. Add User\n4. View Users\n5. Issue Book\n6. View Issued Books\n7. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: addBook(); break;
                case 2: viewBooks(); break;
                case 3: addUser(); break;
                case 4: viewUsers(); break;
                case 5: issueBook(); break;
                case 6: viewIssuedBooks(); break;
                case 7: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid option.");
            }
        }
    }
}