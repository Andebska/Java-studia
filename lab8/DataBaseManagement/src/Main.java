import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SqlQuery sqlQuery = new SqlQuery();
        Scanner scanner = new Scanner(System.in);
        int choice;

    do {
        System.out.println("\nMenu:");
        System.out.println("1. Wyświetl wszystkie dane");
        System.out.println("2. Wyszukaj dane po autorze");
        System.out.println("3. Wyszukaj dane po ISBN");
        System.out.println("4. Dodaj nową książkę");
        System.out.println("5. Wyjście");
        System.out.print("Wybierz opcję: ");
        choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                List<Book> allBooks = sqlQuery.showAllData();
                displayBooks(allBooks);
                break;
            case 2:
                System.out.println("Podaj nazwisko autora: ");
                String author = scanner.nextLine();
                List<Book> booksByAuthor = sqlQuery.searchByAuthor(author);
                displayBooks(booksByAuthor);
                break;
            case 3:
                System.out.println("Podaj ISBN: ");
                String isbn = scanner.nextLine();
                List<Book> booksbyISBN = sqlQuery.searchByISBN(isbn);
                displayBooks(booksbyISBN);
                break;
            case 4:
                System.out.print("Podaj ISBN: ");
                String newIsbn = scanner.nextLine();
                System.out.print("Podaj tytuł: ");
                String title = scanner.nextLine();
                System.out.print("Podaj autora: ");
                String newAuthor = scanner.nextLine();
                System.out.print("Podaj rok wydania: ");
                int year = scanner.nextInt();
                scanner.nextLine();
                Book book = new Book(newIsbn, title, newAuthor, year);
                sqlQuery.addBook(book);
                break;
            case 5:
                System.out.println("Koniec programu.");
                break;
            default:
                System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
        }
    } while (choice != 5);
    scanner.close();
    }

    private static void displayBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("Lista książek jest pusta");
        } else {
            for (Book book : books) {
                System.out.println("ISBN: " + book.getIsbn() + ", Tytuł: " + book.getTitle() + ", Autor: " + book.getAuthor() + ", Rok: " + book.getYear());
            }
        }
    }
}
