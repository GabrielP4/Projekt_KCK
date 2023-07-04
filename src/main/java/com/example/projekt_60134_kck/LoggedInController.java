package com.example.projekt_60134_kck;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;
public class LoggedInController implements Initializable{
    @FXML
    private Button btn_logout;
    @FXML
    private Button btn_send;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "first-page.fxml", "Zaloguj się");
            }
        });
        btn_send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Email.Sender();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}
