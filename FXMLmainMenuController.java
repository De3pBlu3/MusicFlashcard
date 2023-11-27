import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
public class FXMLmainMenuController {
    public Button signoutButton;
    public Button qioButton;
    public Button statsButton;
    public Button playButton;

    @FXML
    public void initialize() {
        signoutButton.setOnAction(e -> {
            System.out.println("Signing out");
            launcher.user_ID = null;
            System.out.println("Successful logout");
            try {
                Stage stage = (Stage) playButton.getScene().getWindow();
                stage.setScene(FXMLloader.loadScene("FXML/Landingpage.fxml"));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        qioButton.setOnAction(e -> {
            System.out.println("Inputting questions");
            try {
                Stage stage = (Stage) playButton.getScene().getWindow();
                stage.setScene(FXMLloader.loadScene("FXML/Questionio.fxml"));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        statsButton.setOnAction(e -> {
            System.out.println("Viewing stats");
            try {
                Stage stage = (Stage) playButton.getScene().getWindow();
                stage.setScene(FXMLloader.loadScene("FXML/Stats.fxml"));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        playButton.setOnAction(e -> {
            System.out.println("Playing");
            try {
                Stage stage = (Stage) playButton.getScene().getWindow();
                stage.setScene(FXMLloader.loadScene("FXML/Gamemode.fxml"));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}
