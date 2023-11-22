import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FXMLlandingController {

    public Button loginButton;
    public Button signUpButton;

    public Button exitButton;

    @FXML
    public void initialize() {

        loginButton.setOnAction(e -> {
            Stage stage = (Stage) loginButton.getScene().getWindow();

            try {
                stage.setScene(FXMLloader.loadScene("FXML/login.fxml"));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        signUpButton.setOnAction(e -> {
            Stage stage = (Stage) loginButton.getScene().getWindow();

            try {
                stage.setScene(FXMLloader.loadScene("FXML/Signup.fxml"));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        exitButton.setOnAction(e -> {
            Stage stage = (Stage) loginButton.getScene().getWindow();

            stage.close();
        });
    }


}
