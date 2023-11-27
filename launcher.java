
import javafx.application.Application; //Setting up!! Make sure you dont have src folder and that when making package, you click the second option
import javafx.scene.Scene;
import javafx.stage.Stage;

	//Scenes = start lowercase
	//Layouts = Start uppercase
	//Controls = start lowercase

	public class launcher extends Application {

		static String user_ID;
		static int lastScore;
		public static void main(String[] args) {
			// init db
			Questions.main(args);
			launch(args);  //method in application class that sets up javafx app (setup)
		}

		@Override
		public void start(Stage window) throws Exception {
			window.setTitle("CUiz"); //Window title
			Scene scene = FXMLloader.loadScene("FXML/Landingpage.fxml");
//			Scene scene = statGUI.createScene(window);
			Application.setUserAgentStylesheet("theme/nord-dark.css");      // set the theme!

			window.setScene(scene);
			window.show();

		}
	}




	
	
	
//	Ellice 0-216