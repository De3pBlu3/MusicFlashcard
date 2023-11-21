import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class FXMLSignupController {

    public PasswordField passwordField;
    public PasswordField confirmPassword;

    public TextField usernameField;
    public Button createAccountButton;
    public Button loginButton;

    Popup existingUserPopup = new Popup() ;
    Label existingUserPLabel = new Label("Something has gone wrong, most likely the username already exists. Try again. ");


    // login button
    @FXML
    // init
    public void initialize() {
        createAccountButton.setOnAction(e -> {
            String user = usernameField.getText();        //Changes user input into String to check in db
            String pass = passwordField.getText();
            String confirmPass = confirmPassword.getText();

            if (Objects.equals(confirmPass, pass)) { //tests whether the passwords  are equal
                // password check

                if (passwordCheck(pass) && DB_UserInteract.insert(user, pass)) { // new user name and password stored if it doesn't exist, and password is valid
                    System.out.println("New user created successfully");
                    Stage stage = (Stage) createAccountButton.getScene().getWindow();
                    try {
                        stage.setScene(FXMLloader.loadScene("FXML/login.fxml")); // go to login screen
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    System.out.println("Something has gone wrong, most likely the username already exists. Try again. "); //user exists and was not stored again
                    failPopUp(existingUserPopup, (Stage) confirmPassword.getScene().getWindow());
                    colorChange(confirmPassword, passwordField);
                    colorChange(usernameField, passwordField);
                }
            } else {
                System.out.println("Passwords do not match.. ");
                colorChange(confirmPassword, passwordField);

            }



        });
        loginButton.setOnAction(e -> {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            try {
                stage.setScene(FXMLloader.loadScene("FXML/login.fxml"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
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
    public static void failPopUp(Popup existingUserPopup, Stage window) {
        Timeline popupTimeline = new Timeline( //To store keyframes
                new KeyFrame(Duration.seconds(0), e -> {
                    existingUserPopup.show(window);
                }),
                new KeyFrame(Duration.seconds(3), e -> {
                    existingUserPopup.hide();
                }));
        popupTimeline.setCycleCount(1); //How many times it plays the sequence
        popupTimeline.playFromStart();
    }

    public static boolean passwordCheck(String password) {
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());
        boolean hasNumber = password.matches(".*\\d.*");
        boolean length = password.length() >= 8;
        return hasUppercase && hasLowercase && hasNumber && length;
    }
}
