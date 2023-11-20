import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class FXMLloader {
    public static Scene loadScene(String url) throws IOException {
        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(new URL(url));
        loader.setLocation(FXMLloader.class.getResource(url));
        // gridpane is the name of the fx:id of the root element in the fxml file
        VBox root = loader.load();
        return new Scene(root);
    }

}
