import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

	public class launcher extends Application {

		static String user_ID;
		static int lastScore;
		public static void main(String[] args) {
			// init db
//			Questions.main(args);
			launch(args);
		}

		@Override
		public void start(Stage window) throws Exception {
			window.setTitle("CUiz"); //Window title
			Scene scene = FXMLloader.loadScene("FXML/Landingpage.fxml");
			Application.setUserAgentStylesheet("theme/nord-dark.css");      // set the theme!

			window.setScene(scene);
			window.show();

		}
	}