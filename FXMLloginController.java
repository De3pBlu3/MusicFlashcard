import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLloginController {
        @FXML
        private Button loginConfirmButton;
        @FXML
        private TextField usernameField;
        @FXML
        private PasswordField passwordField;
        @FXML
        private Button signUpButton;


        // on login button click

    @FXML
    public void initialize() {
        loginConfirmButton.setOnAction(this::confirmLogin);
        signUpButton.setOnAction(e ->  {
            Stage stage = (Stage) signUpButton.getScene().getWindow();
            try {
                stage.setScene(FXMLloader.loadScene("FXML/Signup.fxml"));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    @FXML
    public void confirmLogin(ActionEvent event) {
        String user = usernameField.getText();        //Changes user input into String to check in db
        String pass = passwordField.getText();

        if (!DB_UserInteract.loginCheck(user, pass)) {    //Checks whether user name and password exists
            colorChange(usernameField, passwordField);            //If it doesnt exist then go red
            System.out.println("Username or password does not match. Try again"); //loop continues
        } else {
            System.out.println("Successful login");
            launcher.user_ID = user;
            Stage stage = (Stage) loginConfirmButton.getScene().getWindow();
            try {

                stage.setScene(FXMLloader.loadScene("FXML/MainMenu.fxml"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void colorChange(TextField usernameField, PasswordField passwordField) {
        Timeline colorChangeTimeline = new Timeline( //To store keyframes
                new KeyFrame(Duration.seconds(0), e -> { // Change the text field's background color for 2 seconds
                    usernameField.setStyle("-fx-background-color: red;");
                    passwordField.setStyle("-fx-background-color: red;");

                }),
                new KeyFrame(Duration.seconds(1), e -> {// Revert the color after 3 seconds
                    usernameField.setStyle("");
                    passwordField.setStyle("");
                }));
        colorChangeTimeline.setCycleCount(1); //How many times it plays the sequence
        colorChangeTimeline.playFromStart(); //play

    }

}
