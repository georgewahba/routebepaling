//
// Created by: Groep 7 2.0
//java code voor de routebepaling applicatie
//

//NOTE: LEES EN VOLG EERST HET README.MD BESTAND

//java paketten imporeren
import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.*;
import java.awt.*;
import java.net.URI;
import javax.swing.border.Border;

public class Routebepaling_scherm extends JFrame {
    //database gegevens voor de connectie.
    //url van de database :3306 is de poort(verander dit als je een andere poort gebruikt)
    //routebepaling is de database naam(verander dit als je een andere database naam gebruikt)
    static String url = "jdbc:mysql://localhost:3306/routebepaling";
    //root is de username van de database(verander dit als je een andere gebruikersnaam gebruikt)
    //password is het wachtwoord van de database(verander dit als je een ander wachtwoord gebruikt)
    static String username = "root", password = "";

    public static void main(String[] args) {
        //het aanmaken van het scherm
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        //scherm is lang en smal zodat het op een mobiel scherm lijkt
        frame.setSize(900, 1000);
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

        // Login mislukt, error label wordt toegevoegd
        JLabel errorLabel = new JLabel("Incorrect username or password!");
        errorLabel.setBounds(10, 120, 250, 25);
        errorLabel.setForeground(Color.RED);
        //einde login scherm

        button.addActionListener(e -> {
            //wanneer de login knop wordt ingedrukt word er een check gedaan of de ingevoerde gegevens kloppen
            //ophalen van de ingevoerde gegevens
            String user = textfield.getText();
            String pass = passwordfield.getText();
            //fout login notificatie wordt verwijderd
            panel.remove(errorLabel);

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
                        //query voor het ophalen van de adressen gekoppeld aan de ingelogde bezorger
                        PreparedStatement adresses = connection.prepareStatement("SELECT * FROM adressen WHERE route_id = ? AND is_completed = 0 order by postcode asc");;
                        // user wordt toegevoegd op de plek van de plek van de ?
                        adresses.setString(1, String.valueOf(resultSet.getInt("id")));
                        ResultSet resultSet2 = adresses.executeQuery();
                        int pos = 0;
                        while (resultSet2.next()) {
                            // voor elk adres wordt er een label toegevoegd
                            JLabel route1 = new JLabel("Route: adres" + resultSet2.getInt("id") + " " + resultSet2.getString("straatnaam") + " " + resultSet2.getString("huisnummer") + " " + resultSet2.getString("stad") + " " + resultSet2.getString("postcode"));
                            route1.setBounds(10, 150 + pos * 20, 400, 25);
                            panel.add(route1);

                            // de "navigeer naar adres" knop word toegevoegd, als je hierop drukt gaat je browser naar google maps en navigeert naar het adres
                            JButton navigeer_button = new JButton("Navigeer adres");
                            navigeer_button.setBounds(320, 150 + pos * 20, 150, 25);
                            navigeer_button.setBorder(new RoundedBorder(10));
                            panel.add(navigeer_button);

                            // attributen van het adres maken zonder spaties(andrs werkt de maps url niet) om ze in de maps url te gebruiken
                            String straatnaam = resultSet2.getString("straatnaam").replaceAll("\\s+", "");
                            String huisnummer = resultSet2.getString("huisnummer").replaceAll("\\s+", "");
                            String stad = resultSet2.getString("stad").replaceAll("\\s+", "");
                            String postcode = resultSet2.getString("postcode").replaceAll("\\s+", "");

                            navigeer_button.addActionListener(e1 -> {

                                //browser openen naar google maps met het adres
                                try {
                                    Desktop.getDesktop().browse(new URI("https://www.google.com/maps/place/" + straatnaam + "+" + huisnummer + "+" + stad + "+" + postcode));
                                } catch (IOException | URISyntaxException ex) {
                                    throw new RuntimeException(ex);
                                }
                            });

                            // de "bezorgd" knop word toegevoegd, als je hierop drukt word de route verwijderd dit duid an dat het pakketje is bezorgd
                            // groen
                            JButton bezorgd_button = new JButton("Bezorgd");
                            Color bezorgd_color = new Color(0,204,0);
                            bezorgd_button.setBounds(475, 150 + pos * 20, 120, 25);
                            bezorgd_button.setBackground(bezorgd_color);
                            bezorgd_button.setBorder(new RoundedBorder(10));
                            bezorgd_button.setOpaque(true);
                            panel.add(bezorgd_button);

                            // oranje
                            JButton buren_button = new JButton("Bezorg bij buren");
                            Color buren_color = new Color(255,102,0);
                            buren_button.setBounds(600, 150 + pos * 20, 150, 25);
                            buren_button.setBackground(buren_color);
                            buren_button.setBorder(new RoundedBorder(10));
                            buren_button.setOpaque(true);
                            panel.add(buren_button);

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
                                panel.remove(buren_button);
                                panel.revalidate();
                                panel.repaint();
                            });


                            buren_button.addActionListener(e14 -> {
                                //begin dialoogvenster with 1 label for huisnummer and 1 textfield for huisnumme
                            JFrame JDialog = new JFrame();
                            JDialog.setSize(300, 300);
                            JDialog.setLayout(null);
                            JDialog.setVisible(true);

                            JLabel huisnummer_label = new JLabel("Huisnummer");
                            huisnummer_label.setBounds(10, 10, 80, 25);
                            JDialog.add(huisnummer_label);

                            JTextField huisnummer_textfield = new JTextField();
                            huisnummer_textfield.setBounds(100, 10, 80, 25);
                            JDialog.add(huisnummer_textfield);

                            JButton confirm_button = new JButton("Confirm");
                            confirm_button.setBounds(10, 80, 80, 25);
                            JDialog.add(confirm_button);

                            confirm_button.addActionListener(e15 -> {
                                //huisnummer van de buren wordt opgehaald
                                String huisnummer_buren = huisnummer_textfield.getText();
                                //huisnummer van de buren wordt in de database gezet
                                try {
                                    PreparedStatement buren = connection.prepareStatement("UPDATE adressen SET bij_buren = ?, is_completed = 1 WHERE id = ?");
                                    buren.setString(1, huisnummer_buren);
                                    buren.setString(2, String.valueOf(id));
                                    buren.executeUpdate();

                                    panel.remove(route1);
                                    panel.remove(navigeer_button);
                                    panel.remove(bezorgd_button);
                                    panel.remove(buren_button);
                                    panel.revalidate();
                                    panel.repaint();
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                                //dialoogvenster wordt gesloten
                                JDialog.dispose();
                            });

                            JButton cancel_button = new JButton("Cancel");
                            cancel_button.setBounds(100, 80, 80, 25);
                            JDialog.add(cancel_button);

                            cancel_button.addActionListener(e16 -> {
                                //dialoogvenster wordt gesloten
                                JDialog.dispose();
                            });

                            JDialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                            });

                            //niet bezorgd knop, als hierop gelikt wordt wordt de route verwijderd voor de bezorger en en wordt het voor de volgende dag opnieuw ingepland
                            // rood
                            JButton niet_bezorgd_button = new JButton("Niet bezorgd");
                            Color niet_bezorgd_color = new Color(255,51,51);
                            niet_bezorgd_button.setBounds(755, 150 + pos * 20, 120, 25);
                            niet_bezorgd_button.setBackground(niet_bezorgd_color);
                            niet_bezorgd_button.setBorder(new RoundedBorder(10));
                            niet_bezorgd_button.setOpaque(true);
                            panel.add(niet_bezorgd_button);

                            niet_bezorgd_button.addActionListener(e17 -> {
                                //route wordt visureel verwijderd
                                //route_id wordt weer op 0 gezet zodat de route opnieuw ingepland kan worden
                                try {
                                    PreparedStatement completed = connection.prepareStatement("UPDATE adressen SET route_id = null WHERE id = ?");
                                    completed.setString(1, String.valueOf(id));
                                    completed.executeUpdate();

                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }

                                panel.remove(route1);
                                panel.remove(navigeer_button);
                                panel.remove(bezorgd_button);
                                panel.remove(buren_button);
                                panel.remove(niet_bezorgd_button);
                                panel.revalidate();
                                panel.repaint();
                            });

                            pos = pos + 2;
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
                    //foute login notificatie wordt laten zien bij foute login
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
