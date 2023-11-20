import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.net.URL;

public class FXMLLogin extends Application{


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = FXMLloader.loadScene("FXML/Login.fxml");



//        ReturnsignUpButton.setOnAction(e -> {
//            Stage stage = (Stage) ReturnsignUpButton.getScene().getWindow();
//            stage.setScene(signupScene.createScene(stage));
//        });
//
        // set up onAction for the button in FXML

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
