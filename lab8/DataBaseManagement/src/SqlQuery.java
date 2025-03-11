import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlQuery {
    public List<Book> showAllData() {
        return executeQuery("SELECT * FROM books;");
    }

    public List<Book> searchByAuthor(String author) {
        return executeQuery("SELECT * FROM books WHERE author LIKE '% " + author + "%';");
    }

    public List<Book> searchByISBN(String isbn) {
        return executeQuery("SELECT * FROM books WHERE isbn = '" + isbn+ "';");
    }

    public void addBook(Book book) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = createConnection();
            if (connection != null) {
                String query = "INSERT INTO books (isbn, title, author, year) VALUES (?, ?, ?, ?);";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, book.getIsbn());
                preparedStatement.setString(2, book.getTitle());
                preparedStatement.setString(3, book.getAuthor());
                preparedStatement.setInt(4, book.getYear());
                preparedStatement.executeUpdate();
                System.out.println("Książka została dodana");
            }
        } catch (SQLException e) {
            System.out.println("Błąd przy dodawaniu książki: " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                DBConnection.closeConnection(connection);
            } catch (SQLException e) {
                System.out.println("Błąd przy zamykaniu zasobów: " + e.getMessage());
            }
        }
    }

    private Connection createConnection() {
        int attempts = 0;
        Connection connection = null;
        while (attempts < 3) {
            connection = DBConnection.createConnection();
            if (connection != null) {
                return connection;
            }
            attempts++;
            System.out.println("Próba połączenia nr " + attempts);
        }
        System.out.println("Nie udało się nawiązać połączenia");
        System.exit(1);
        return null;
    }

    public List<Book> executeQuery(String query) {
        List<Book> books = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Tworzenie połączenia z bazą danych
            connection = createConnection();
            if (connection != null) {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);

                // Przetwarzanie wyników
                while (resultSet.next()) {
                    String isbn = resultSet.getString("isbn");
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("author");
                    int year = resultSet.getInt("year");
                    books.add(new Book(isbn, title, author, year));
                }
            }
        } catch (SQLException e) {
            System.out.println("Błąd przy wykonaniu zapytania: " + e.getMessage());
        } finally {
            // Zamknięcie zasobów
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                DBConnection.closeConnection(connection);
            } catch (SQLException e) {
                System.out.println("Błąd przy zamykaniu zasobów: " + e.getMessage());
            }
        }
        return books;
    }
}
