//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLgameplayController {
    static int gamemodeInt = 0;
    card[] cards_array;
    static int score;
    static int wins;
    static int losses;
    static int i = 0;
    @FXML
    private RadioButton ans1Checkbox;
    @FXML
    private RadioButton ans2Checkbox;
    @FXML
    private RadioButton ans3Checkbox;
    @FXML
    private RadioButton ans4Checkbox;
    @FXML
    private Button nextButton;
    @FXML
    private Label questionLabel;
    @FXML
    private Label scoreLabel;
    Popup incorrectP = new Popup();
    Label incorrectPLabel = new Label("Incorrect");
    Popup correctP = new Popup();
    Label correctPLabel = new Label("Correct");

    public FXMLgameplayController() {
    }

    @FXML
    private void initialize() {
        card[] cards_array = DB_CardInteract.allCardsIncreasingDifficulty();
        if (gamemodeInt == 0) {
            cards_array = DB_CardInteract.allCardsIncreasingDifficulty();
        } else if (gamemodeInt == 1) {
            cards_array = DB_CardInteract.allCardsIncreasingScore(launcher.user_ID);
        } else if (gamemodeInt == 2) {
            cards_array = DB_CardInteract.returnAllCards();
            shuffle_array(cards_array);
        } else {
            System.out.println("Error: gamemodeInt not set");
        }

        this.questionLabel.setText(cards_array[i].Question_content);
        this.scoreLabel.setText("Current Score: " + score);
        if (cards_array[i].Question_answers_arr.length == 4) {
            this.ans1Checkbox.setText(cards_array[i].Question_answers_arr[0]);
            this.ans2Checkbox.setText(cards_array[i].Question_answers_arr[1]);
            this.ans3Checkbox.setText(cards_array[i].Question_answers_arr[2]);
            this.ans4Checkbox.setText(cards_array[i].Question_answers_arr[3]);
        } else if (cards_array[i].Question_answers_arr.length == 3) {
            this.ans1Checkbox.setText(cards_array[i].Question_answers_arr[0]);
            this.ans2Checkbox.setText(cards_array[i].Question_answers_arr[1]);
            this.ans3Checkbox.setText(cards_array[i].Question_answers_arr[2]);
            this.ans4Checkbox.setText(" ");
            this.ans4Checkbox.setVisible(false);
        } else {
            this.ans1Checkbox.setText(cards_array[i].Question_answers_arr[0]);
            this.ans2Checkbox.setText(cards_array[i].Question_answers_arr[1]);
            this.ans3Checkbox.setText(" ");
            this.ans4Checkbox.setText(" ");
            this.ans3Checkbox.setVisible(false);
            this.ans4Checkbox.setVisible(false);
        }

        card[] finalCards_array = cards_array;
        this.nextButton.setOnAction((e) -> {
            Stage stage = (Stage)this.nextButton.getScene().getWindow();
            if (AnswerSelection(this.ans1Checkbox, this.ans2Checkbox, this.ans3Checkbox, this.ans4Checkbox) == finalCards_array[i].Question_correct_answer) {
                DB_ScoreInteract.addWin(launcher.user_ID, finalCards_array[i].card_id);
                ++score;
                ++wins;
                CorrectPopUp(this.correctP, stage);
            } else {
                DB_ScoreInteract.addLoss(launcher.user_ID, finalCards_array[i].card_id);
                --score;
                ++losses;
                IncorrectPopUp(this.incorrectP, stage);
            }

            if (i < finalCards_array.length - 1) {
                System.out.println("i = " + i);
                System.out.println("cards_array.length = " + finalCards_array.length);
                ++i;
            } else {
                DB_PlayHistory.addHistory(launcher.user_ID, score, wins, losses);
                stage.setScene(postgameScene.createScene(stage, score));
                stage.setFullScreen(true);
                resetScore();
            }

            System.out.println(score);
            this.questionLabel.setText(finalCards_array[i].Question_content);
            this.scoreLabel.setText("Current Score:" + score);
            if (finalCards_array[i].Question_answers_arr.length == 4) {
                this.ans1Checkbox.setText(finalCards_array[i].Question_answers_arr[0]);
                this.ans2Checkbox.setText(finalCards_array[i].Question_answers_arr[1]);
                this.ans3Checkbox.setText(finalCards_array[i].Question_answers_arr[2]);
                this.ans4Checkbox.setText(finalCards_array[i].Question_answers_arr[3]);
            } else if (finalCards_array[i].Question_answers_arr.length == 3) {
                this.ans1Checkbox.setText(finalCards_array[i].Question_answers_arr[0]);
                this.ans2Checkbox.setText(finalCards_array[i].Question_answers_arr[1]);
                this.ans3Checkbox.setText(finalCards_array[i].Question_answers_arr[2]);
                this.ans4Checkbox.setText(" ");
                this.ans4Checkbox.setVisible(false);
            } else {
                this.ans1Checkbox.setText(finalCards_array[i].Question_answers_arr[0]);
                this.ans2Checkbox.setText(finalCards_array[i].Question_answers_arr[1]);
                this.ans3Checkbox.setText(" ");
                this.ans4Checkbox.setText(" ");
                this.ans3Checkbox.setVisible(false);
                this.ans4Checkbox.setVisible(false);
            }

        });
    }

    public static void shuffle_array(card[] cards_array) {
        for(int i = 0; i < cards_array.length; ++i) {
            int randomIndexToSwap = (int)(Math.random() * (double)cards_array.length);
            card temp = cards_array[randomIndexToSwap];
            cards_array[randomIndexToSwap] = cards_array[i];
            cards_array[i] = temp;
        }

    }

    public static int AnswerSelection(RadioButton Answer1ckBox, RadioButton Answer2ckBox, RadioButton Answer3ckBox, RadioButton Answer4ckBox) {
        int total = 0;
        int user_answer = 9;
        if (Answer1ckBox.isSelected()) {
            user_answer = 0;
            ++total;
        }

        if (Answer2ckBox.isSelected()) {
            user_answer = 1;
            ++total;
        }

        if (Answer3ckBox.isSelected()) {
            user_answer = 2;
            ++total;
        }

        if (Answer4ckBox.isSelected()) {
            user_answer = 3;
            ++total;
        }

        return total == 1 ? user_answer : 9;
    }

    public static void CorrectPopUp(Popup correctP, Stage stage) {
        Timeline popupTimeline = new Timeline(new KeyFrame[]{new KeyFrame(Duration.seconds(0.0), (e) -> {
            correctP.show(stage);
        }, new KeyValue[0]), new KeyFrame(Duration.seconds(2.0), (e) -> {
            correctP.hide();
        }, new KeyValue[0])});
        popupTimeline.setCycleCount(1);
        popupTimeline.playFromStart();
    }

    public static void IncorrectPopUp(Popup incorrectP, Stage stage) {
        Timeline popupTimeline = new Timeline(new KeyFrame[]{new KeyFrame(Duration.seconds(0.0), (e) -> {
            incorrectP.show(stage);
        }, new KeyValue[0]), new KeyFrame(Duration.seconds(2.0), (e) -> {
            incorrectP.hide();
        }, new KeyValue[0])});
        popupTimeline.setCycleCount(1);
        popupTimeline.playFromStart();
    }

    public static void resetScore() {
        score = 0;
        wins = 0;
        losses = 0;
        i = 0;
    }
}
