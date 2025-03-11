import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://sql.freedb.tech:3306/freedb_Biblioteka";
    private static final String USER = "freedb_biblioteka_user1";
    private static final String PASSWORD = "uzv9RMMJe&Rn?6%";

    public static Connection createConnection() {
        Connection connection = null;
        try {
            // Rejestracja sterownika JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Tworzenie połączenia
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Połączenie z bazą danych zostało nawiązane.");
        } catch (ClassNotFoundException e) {
            System.out.println("Sterownik JDBC nie został znaleziony: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Błąd przy połączeniu z bazą danych: " + e.getMessage());
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Połączenie z bazą danych zostało zamknięte.");
            } catch (SQLException e) {
                System.out.println("Błąd przy zamykaniu połączenia: " + e.getMessage());
            }
        }
    }
}
