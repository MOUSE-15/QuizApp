package com.example.quizapp;

import com.example.quizapp.DBConn.DBAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    public TextField username;

    @FXML
    public TextField indexNumber;

    public boolean login(String name, String studentIndex){
        String query = "SELECT * FROM users WHERE name = ? and indexNumber = ?";
        try{
        PreparedStatement preparedStatement= DBAccess.connect().prepareStatement(query);
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,studentIndex);
        ResultSet resultSet= preparedStatement.executeQuery();
        return resultSet.next();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void onclickProceed(ActionEvent event) throws IOException {
        String userName = username.getText();
        String indexnumber = indexNumber.getText();


        if(userName == null || userName.isEmpty()|| indexnumber==null || indexnumber.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText("Index Number or Username should not be empty");
            return;
        }

        boolean authorizeUser = login(userName,indexnumber);
        if (authorizeUser){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InstructionScreen.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Instruction");
            stage.setScene(scene);
            stage.show();

        }

    }
}