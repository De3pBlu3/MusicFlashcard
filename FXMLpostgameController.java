import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FXMLpostgameController{
    public Label scorelabel;
    public Button mainMenuButton;
    public Button statButton;

@FXML
    public void initialize() {
        scorelabel.setText(String.valueOf(launcher.lastScore));
        mainMenuButton.setOnAction(e -> {
            System.out.println("Going to main menu");
            try {
                Stage stage = (Stage) mainMenuButton.getScene().getWindow();
                stage.setScene(FXMLloader.loadScene("FXML/Mainmenu.fxml"));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        statButton.setOnAction(e -> {
            System.out.println("Viewing stats");
            try {
                Stage stage = (Stage) mainMenuButton.getScene().getWindow();
                stage.setScene(FXMLloader.loadScene("FXML/Stats.fxml"));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}