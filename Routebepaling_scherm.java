//
// Created by: George Wahba
//java code voor de routebepaling applicatie
//

//java paketten imporeren
import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.*;
import java.awt.*;
import java.net.URI;

public class Routebepaling_scherm extends JFrame {
    //database gegevens voor de connectie
    //url van de database :3306 is de poort(verander dit als je een andere poort gebruikt)
    //roiutebepaling is de database naam(verander dit als je een andere database naam gebruikt)
    static String url = "jdbc:mysql://localhost:3306/routebepaling";
    //root is de username van de database(verander dit als je een andere gebruikersnaam gebruikt)
    //password is het wachtwoord van de database(verander dit als je een ander wachtwoord gebruikt)
    static String username = "root", password = "";

    public static void main(String[] args) {
        //het aanmaken van het scherm
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        //scherm is lang en smal zodat het op een mobiel scherm lijkt
        frame.setSize(700, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        //begin login scherm
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
        //einde login scherm

        button.addActionListener(e -> {
            //wanneer de login knop wordt ingedrukt word er een check gedaan of de ingevoerde gegevens kloppen
            //ophalen van de ingevoerde gegevens
            String user = textfield.getText();
            String pass = passwordfield.getText();

            try {
                //connectie met de database
                Connection connection = DriverManager.getConnection(url, username, password);
                //query om de ingevoerde gegevens te checken
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
                //de ingevoerde gegevens worden in de query gezet op de plek van de ?
                statement.setString(1, user);
                statement.setString(2, pass);
                //resultaat van de query
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    //wanneer de ingevoerde gegevens kloppen word er een welkomst bericht weergegeven in het groen
                    JLabel successLabel = new JLabel("Welcome, " + user);
                    successLabel.setForeground(Color.getHSBColor(0.3f, 0.8f, 0.8f));
                    successLabel.setBounds(10, 110, 200, 25);
                    panel.add(successLabel);

                    //de tekst je route van vandaag word toegevoegd
                    JLabel route = new JLabel("Je route van vandaag");
                    route.setBounds(10, 130, 200, 25);
                    panel.add(route);

                    try {
                        //query voor het ophalen van de adressen gekoppeld aan de ingelogde gebruiker
                        PreparedStatement adresses = connection.prepareStatement("SELECT * FROM adressen WHERE route_id = ? AND is_completed = 0");
                        // user is toevoegen op de plek van de plek van de ?
                        adresses.setString(1, String.valueOf(resultSet.getInt("id")));
                        ResultSet resultSet2 = adresses.executeQuery();
                        int pos = 0;
                        while (resultSet2.next()) {
                            // voor elk adres word er een labeltoegevoegd
                            JLabel route1 = new JLabel("Route: adres" + resultSet2.getInt("id") + " " + resultSet2.getString("straatnaam") + " " + resultSet2.getString("huisnummer") + " " + resultSet2.getString("stad") + " " + resultSet2.getString("postcode"));
                            route1.setBounds(10, 150 + pos * 20, 400, 25);
                            panel.add(route1);

                            // de "navigeer naar adres" knop word toegevoegd, als je hierop drukt gaat je browser naar google maps en navigeert naar het adres
                            JButton navigeer_button = new JButton("navigeer adres");
                            navigeer_button.setBounds(350, 150 + pos * 20, 120, 25);
                            panel.add(navigeer_button);

                            // attributen van het adres maken zonder spaties(andrs werkt de maps url niet) om ze in de maps url te gebruiken
                            String straatnaam = resultSet2.getString("straatnaam").replaceAll("\\s+", "");
                            String huisnummer = resultSet2.getString("huisnummer").replaceAll("\\s+", "");
                            String stad = resultSet2.getString("stad").replaceAll("\\s+", "");
                            String postcode = resultSet2.getString("postcode").replaceAll("\\s+", "");

                            navigeer_button.addActionListener(e1 -> {

                                //browser openen naar google maps met het adres
                                try {
                                    Desktop.getDesktop().browse(new URI("https://www.google.com/maps/place/" + straatnaam + "+" + huisnummer + "+" + stad + "+" + postcode) );
                                } catch (IOException | URISyntaxException ex) {
                                    throw new RuntimeException(ex);
                                }
                            });

                            // de "bezorgd" knop word toegevoegd, als je hierop drukt word de route verwijderd dit duid an dat het pakketje is bezorgd
                            JButton bezorgd_button = new JButton("bezorgd");
                            bezorgd_button.setBounds(500, 150 + pos * 20, 120, 25);
                            panel.add(bezorgd_button);

                            int id = resultSet2.getInt("id");
                            bezorgd_button.addActionListener(e12 -> {
                                //route wordt visureel verwijderd
                                //route wordt in de tabel op compleet gezet
                                try {
                                    PreparedStatement completed = connection.prepareStatement("UPDATE adressen SET is_completed = 1 WHERE id = ?");
                                    completed.setString(1, String.valueOf(id));
                                    completed.executeUpdate();

                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }

                                panel.remove(route1);
                                panel.remove(navigeer_button);
                                panel.remove(bezorgd_button);
                                panel.revalidate();
                                panel.repaint();
                            });
                            pos++;
                        }

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    // logout knop wordt toegevoegd
                    JButton logoutButton = new JButton("Logout");
                    logoutButton.setBounds(10, 80, 80, 25);
                    panel.add(logoutButton);
                    logoutButton.addActionListener(e13 -> {
                        //alle account gerelateerde componenten worden verwijderd(naam/logout knop/adressen)
                        panel.removeAll();
                        panel.revalidate();
                        panel.repaint();

                        //opnieuw inloggen
                        panel.add(log_label);
                        panel.add(label);
                        panel.add(textfield);
                        panel.add(label2);
                        panel.add(passwordfield);
                        panel.add(button);
                        panel.revalidate();
                        panel.repaint();

                    });

                    //wanneer ingelogd is de inlogform niet meer nodig dus wordt deze verwijderd
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
                    // Login mislukt, error label wordt toegevoegd
                    JLabel errorLabel = new JLabel("Incorrect username or password!");
                    //**BUG: error label overlapt gelukt label wanneer er eerst fout is ingelogd en daarna goed is ingelogd**
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
        });

        frame.setVisible(true);
    }
}
