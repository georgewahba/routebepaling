import java.sql.*;

public class Database {
    static String url = "jdbc:mysql://localhost:3306/routebepaling";
    static String username = "root", password = "";


    public static void getConnection() {
        try {
            Connection connection =
                    DriverManager.getConnection(url, username, password);
            if (connection != null) {
                System.out.println("Verbinding met SQLite-database is succesvol!");
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM users");
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    System.out.println(id + " " + username + " " + password);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
