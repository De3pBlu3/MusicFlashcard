import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLquestionio {
    public TextField questionField;
    public TextField correctField;
    public TextField inc1Field;
    public TextField inc2Field;
    public TextField inc3Field;


    public RadioButton easyButton;
    public RadioButton mediumButton;
    public RadioButton hardButton;
    public RadioButton csButton;
    public RadioButton dmButton;
    public RadioButton comporgButton;
    public Button addButton;
    public Button returnButton;



    @FXML
    public void initialize(){
        // make sure only one difficulty is selected
        easyButton.setOnAction(e -> {
            mediumButton.setSelected(false);
            hardButton.setSelected(false);
        });
        mediumButton.setOnAction(e -> {
            hardButton.setSelected(false);
            easyButton.setSelected(false);
        });
        hardButton.setOnAction(e -> {
            mediumButton.setSelected(false);
            easyButton.setSelected(false);
        });

        // make sure only one topic is selected
        csButton.setOnAction(e -> {
            dmButton.setSelected(false);
            comporgButton.setSelected(false);
        });
        dmButton.setOnAction(e -> {
            csButton.setSelected(false);
            comporgButton.setSelected(false);
        });
        comporgButton.setOnAction(e -> {
            dmButton.setSelected(false);
            csButton.setSelected(false);
        });

        returnButton.setOnAction(e -> {
            try {
                Stage stage = (Stage) returnButton.getScene().getWindow();
                stage.setScene(FXMLloader.loadScene("FXML/MainMenu.fxml"));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // add question to database
        addButton.setOnAction(e -> {    // TODO error checking


            // user ID
            card user_manual = getCardfromUser();
            DB_CardInteract.addCardToDB(user_manual);

            // create popup to confirm question added
            Popup successfulQAddp = new Popup();
            Label successfulQAddpLabel = new Label("Question Successfully added");
            successfulQAddp.getContent().add(successfulQAddpLabel);
            successfulQAddpLabel.setPadding(new javafx.geometry.Insets(0, 0, 150, 0));
            SuccessPopUp(successfulQAddp, (Stage) returnButton.getScene().getWindow());


        });

    }

    private card getCardfromUser() {
        int rand = (int)(Math.random() * 500)+1; // TODO, this is stupid, fix it

        String card_id= "usr" + rand;


        // get difficulty
        int difficulty = 0;
        if (easyButton.isSelected()) {
            difficulty = 1;
        } else if (mediumButton.isSelected()) {
            difficulty = 2;
        } else if (hardButton.isSelected()) {
            difficulty = 3;
        }

        // get topic
        int topic = 0;
        if (csButton.isSelected()) {
            topic = 1;
        } else if (dmButton.isSelected()) {
            topic = 2;
        } else if (comporgButton.isSelected()) {
            topic = 3;
        }

        // create card and add to DB
        card user_manual = new card(card_id, questionField.getText(), correctField.getText() + ";" + inc1Field.getText() + ";" + inc2Field.getText() + ";" + inc3Field.getText(), 0, topic, difficulty);
        return user_manual;
    }

    public static void SuccessPopUp(Popup successfulQAddp, Stage stage) {
        Timeline popupTimeline = new Timeline( //To store keyframes
                new KeyFrame(Duration.seconds(0), e -> {
                    successfulQAddp.show(stage);
                }),
                new KeyFrame(Duration.seconds(3), e -> {
                    successfulQAddp.hide();
                }));
        popupTimeline.setCycleCount(1); //How many times it plays the sequence
        popupTimeline.playFromStart();
    }
}
