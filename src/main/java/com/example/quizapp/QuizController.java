package com.example.quizapp;

import com.example.quizapp.DBConn.DBAccess;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizController {

    @FXML private Label questionLabel;
    @FXML private RadioButton optionA;
    @FXML private RadioButton optionB;
    @FXML private RadioButton optionC;
    @FXML private Button nextButton;

    @FXML
    private ToggleGroup optionsGroup;
    private List<Question> questions = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private int score = 0;

    @FXML
    public void initialize() {
        // Create toggle group in code
        optionsGroup = new ToggleGroup();
        optionA.setToggleGroup(optionsGroup);
        optionB.setToggleGroup(optionsGroup);
        optionC.setToggleGroup(optionsGroup);

        // Load questions from DB
        loadQuestionsFromDB();
        showQuestion();
    }

    private void loadQuestionsFromDB() {
        String query = "SELECT * FROM questions";

        try {
            PreparedStatement preparedStatement = DBAccess.connect().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                questions.add(new Question(
                        rs.getString("question_text"),
                        rs.getString("option_a"),
                        rs.getString("option_b"),
                        rs.getString("option_c"),
                        rs.getString("correct_option")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void showQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question q = questions.get(currentQuestionIndex);
            questionLabel.setText(q.getQuestion());
            optionA.setText(q.getOptionA());
            optionB.setText(q.getOptionB());
            optionC.setText(q.getOptionC());
            optionsGroup.selectToggle(null);
        } else {
            showFinalScore();
        }
    }

    @FXML
    private void nextQuestion() {
        RadioButton selectedOption = (RadioButton) optionsGroup.getSelectedToggle();
        if (selectedOption != null) {
            String selected = selectedOption.getText();
            Question currentQ = questions.get(currentQuestionIndex);

            // Check if correct
            if (selected.equals(currentQ.getCorrectAnswerText())) {
                score += 5;
            }
            currentQuestionIndex++;
            showQuestion();
        }
    }

    private void showFinalScore() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Quiz Finished");
        alert.setHeaderText("Your Score: " + score);
        alert.setContentText("Do you want to retake the quiz?");
        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");
        alert.getButtonTypes().setAll(yes, no);

        alert.showAndWait().ifPresent(response -> {
            if (response == yes) {
                currentQuestionIndex = 0;
                score = 0;
                showQuestion();
            } else {
                System.exit(0);
            }
        });
    }
}
