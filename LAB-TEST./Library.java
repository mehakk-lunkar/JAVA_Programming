import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    public int bookId;
    public String title;
    public String author;
    public boolean isAvailable;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = true; // By default, a new book is available
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void displayInfo() {
        System.out.println("Book ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Available: " + isAvailable);
    }
}

class ReferenceBook extends Book {
    public int edition;

    public ReferenceBook(int bookId, String title, String author, int edition) {
        super(bookId, title, author);
        this.edition = edition;
    }

    public int getEdition() {
        return edition;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Edition: " + edition);
    }
}

class FictionBook extends Book {
    public String genre;

    public FictionBook(int bookId, String title, String author, String genre) {
        super(bookId, title, author);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Genre: " + genre);
    }
}

class Periodical extends ReferenceBook {
    public String issueFrequency;

    public Periodical(int bookId, String title, String author, int edition, String issueFrequency) {
        super(bookId, title, author, edition);
        this.issueFrequency = issueFrequency;
    }

    public String getIssueFrequency() {
        return issueFrequency;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Issue Frequency: " + issueFrequency);
    }
}

class LibraryManagementSystem {
    public List<Book> books = new ArrayList<>();
    
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully.");
    }

    public void borrowBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId && book.isAvailable()) {
                book.setAvailable(false);
                System.out.println("You have borrowed: " + book.getTitle());
                return;
            }
        }
        System.out.println("Book not available for borrowing.");
    }

    public void returnBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId && !book.isAvailable()) {
                book.setAvailable(true);
                System.out.println("You have returned: " + book.getTitle());
                return;
            }
        }
        System.out.println("This book was not borrowed.");
    }

    public void displayBooks() {
        for (Book book : books) {
            book.displayInfo();
            System.out.println("------------------------------");
        }
    }

    public int countAvailableBooks() {
        int count = 0;
        for (Book book : books) {
            if (book.isAvailable()) count++;
        }
        return count;
    }
}

public class Library {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        LibraryManagementSystem library = new LibraryManagementSystem();

        library.addBook(new FictionBook(1, "The Great Gatsby", "F. Scott Fitzgerald", "Classic"));
        library.addBook(new ReferenceBook(2, "Java Programming", "James Gosling", 3));
        
        while (true) {
            System.out.println("1. Display Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Count Available Books");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    System.out.print("Enter Book ID to borrow: ");
                    int borrowId = scanner.nextInt();
                    library.borrowBook(borrowId);
                    break;
                case 3:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = scanner.nextInt();
                    library.returnBook(returnId);
                    break;
                case 4:
                    System.out.println("Total available books: " + library.countAvailableBooks());
                    break;
                case 5:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}