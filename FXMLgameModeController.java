//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FXMLgameModeController {
    @FXML
    private Button incDifficultyButton;
    @FXML
    private Button incScoreButton;
    @FXML
    private Label loginLabel;
    @FXML
    private Button randomPlayButton;

    public FXMLgameModeController() {
    }

    @FXML
    void initialize() {
        assert this.incDifficultyButton != null : "fx:id=\"incDifficultyButton\" was not injected: check your FXML file 'Gamemode.fxml'.";

        assert this.incScoreButton != null : "fx:id=\"incScoreButton\" was not injected: check your FXML file 'Gamemode.fxml'.";

        assert this.loginLabel != null : "fx:id=\"loginLabel\" was not injected: check your FXML file 'Gamemode.fxml'.";

        assert this.randomPlayButton != null : "fx:id=\"randomPlayButton\" was not injected: check your FXML file 'Gamemode.fxml'.";

        this.loginLabel.setText("Currently logged in as: " + launcher.user_ID);
        this.randomPlayButton.setOnAction((e) -> {
            try {
                Stage stage = (Stage)this.randomPlayButton.getScene().getWindow();
                FXMLgameplayController.gamemodeInt = 2;
                stage.setScene(FXMLloader.loadScene("FXML/Gameplay.fxml"));
            } catch (IOException var3) {
                throw new RuntimeException(var3);
            }
        });
        this.incScoreButton.setOnAction((e) -> {
            try {
                Stage stage = (Stage)this.incScoreButton.getScene().getWindow();
                FXMLgameplayController.gamemodeInt = 1;
                stage.setScene(FXMLloader.loadScene("FXML/Gameplay.fxml"));
            } catch (IOException var3) {
                throw new RuntimeException(var3);
            }
        });
        this.incDifficultyButton.setOnAction((e) -> {
            try {
                Stage stage = (Stage)this.incDifficultyButton.getScene().getWindow();
                FXMLgameplayController.gamemodeInt = 0;
                stage.setScene(FXMLloader.loadScene("FXML/Gameplay.fxml"));
            } catch (IOException var3) {
                throw new RuntimeException(var3);
            }
        });
    }
}
