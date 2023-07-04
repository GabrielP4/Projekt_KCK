package com.example.projekt_60134_kck;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.*;

import java.io.IOException;
public class DBUtils {
    public static void changeScene(ActionEvent event,
                                   String fxmlFile,
                                   String title) {
        Parent root = null;
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 470, 360));
        stage.show();
    }

    public static void signUpUser(ActionEvent actionEvent,
                                  String username,
                                  String userfirstname,
                                  String userlastname,
                                  String password,
                                  String email) {
        Connection connection = null;
        PreparedStatement psInster = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projekt_kck", "root", "ProjektPO2023");
            psCheckUserExist = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckUserExist.setString(1, username);
            resultSet = psCheckUserExist.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("Użytkownik już istnieje");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Nie możesz stworzyć konta z taką nazwą");
                alert.show();
            } else {
                psInster = connection.prepareStatement("INSERT INTO users (username, userfirstname, userlastname, password, email) VALUES(?, ?, ?, ?, ?)");
                psInster.setString(1, username);
                psInster.setString(2, userfirstname);
                psInster.setString(3, userlastname);
                psInster.setString(4, password);
                psInster.setString(5, email);
                psInster.executeUpdate();
                changeScene(actionEvent, "logged-in.fxml", "Witaj");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExist != null) {
                try {
                    psCheckUserExist.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInster != null) {
                try {
                    psInster.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void logInUser(ActionEvent actionEvent,
                                 String username,
                                 String password) {
        Connection connection = null;
        PreparedStatement prepardeStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projekt_kck", "root", "ProjektPO2023");
            prepardeStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
            prepardeStatement.setString(1, username);
            resultSet = prepardeStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Użytkownik nie istnieje");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Podane dane są błędne");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrivedPassword = resultSet.getString("password");
                    if (retrivedPassword.equals(password)) {
                        changeScene(actionEvent, "logged-in.fxml", "Witaj");
                    } else {
                        System.out.println("Błędne hasło");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Podane dane są błędne");
                        alert.show();
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (prepardeStatement != null) {
                try {
                    prepardeStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
