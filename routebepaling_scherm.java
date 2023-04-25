import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.*;

public class routebepaling_scherm extends JFrame {
    static String url = "jdbc:mysql://localhost:3306/routebepaling";
    static String username = "root", password = "";

    public static void main(String[] args) {

        try {
            Connection connection =
                    DriverManager.getConnection(url, username, password);
            if (connection != null) {
                System.out.println("Verbinding met SQLite-database is succesvol!");
                Statement statement = connection.createStatement();
                ResultSet rs =
                        statement.executeQuery(
                                "SELECT * FROM users");
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


        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(360, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel log_label = new JLabel("login:");
        log_label.setBounds(10, 5, 80, 25);
        panel.add(log_label);

        JLabel label = new JLabel("Username:");
        label.setBounds(10, 20, 80, 25);
        panel.add(label);

        JTextField textfield = new JTextField(20);
        textfield.setBounds(100, 20, 165, 25);
        panel.add(textfield);

        JLabel label2 = new JLabel("Password:");
        label2.setBounds(10, 50, 80, 25);
        panel.add(label2);

        JPasswordField passwordfield = new JPasswordField();
        passwordfield.setBounds(100, 50, 165, 25);
        panel.add(passwordfield);

        JButton button = new JButton("login");
        button.setBounds(10, 80, 80, 25);
        panel.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = textfield.getText();
                String pass = passwordfield.getText();

                try {
                    Connection connection = DriverManager.getConnection(url, username, password);
                    PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
                    statement.setString(1, user);
                    statement.setString(2, pass);
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        // Login successful
                        JLabel successLabel = new JLabel("You are logged in!");
                        successLabel.setForeground(Color.getHSBColor(0.3f, 0.8f, 0.8f));
                        successLabel.setBounds(10, 120, 200, 25);
                        panel.add(successLabel);

                        // Add the logout button
                        JButton logoutButton = new JButton("Logout");
                        logoutButton.setBounds(10, 150, 80, 25);
                        panel.add(logoutButton);
                        logoutButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // Remove the logout button
                                panel.remove(successLabel);
                                panel.remove(logoutButton);

                                // Add the form components back to the panel
                                panel.add(log_label);
                                panel.add(label);
                                panel.add(textfield);
                                panel.add(label2);
                                panel.add(passwordfield);
                                panel.add(button);

                                // Repaint the panel
                                panel.revalidate();
                                panel.repaint();
                            }
                        });


                        // Remove the form components from the panel
                        panel.remove(log_label);
                        panel.remove(label);
                        panel.remove(textfield);
                        panel.remove(label2);
                        panel.remove(passwordfield);
                        panel.remove(button);


                        // Repaint the panel
                        panel.revalidate();
                        panel.repaint();
                    } else {
                        // Login failed
                        JLabel errorLabel = new JLabel("Incorrect username or password!");
                        errorLabel.setBounds(10, 120, 250, 25);
                        errorLabel.setForeground(Color.RED);
                        panel.add(errorLabel);

                        // Repaint the panel
                        panel.revalidate();
                        panel.repaint();
                    }

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        frame.setVisible(true);
    }
}
