import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class FXMLloader {
    public static Scene loadScene(String url) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLloader.class.getResource(url));
        VBox root = loader.load();
        return new Scene(root);
    }

}
