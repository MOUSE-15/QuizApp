package com.example.quizapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ResultController {
    public Button retakeButton;
    @FXML private Label scoreLabel;
    private int score;

    public void setScore(int score) {
        this.score = score;
        scoreLabel.setText("Your Score: " + score);
    }

    @FXML
    private void retakeQuiz() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Quiz1.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) scoreLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

