import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
public class postgameScene extends Scene {

        public postgameScene(Stage primaryStage, int score) {
            super(new VBox(), 440, 350);
            
            Background transparentBackground = new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
            Background orangeBackground = new Background(new BackgroundFill(Color.rgb(232, 123, 56), CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
            DropShadow dropShadow = new DropShadow();
       	 
            //postGame contols
            Label gameOverLabel = new Label("Game Over");
            gameOverLabel.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 24));
            gameOverLabel.setTextFill(Color.WHITE);
            gameOverLabel.setBackground(transparentBackground);
            gameOverLabel.setEffect(dropShadow);
            gameOverLabel.setTextAlignment(TextAlignment.CENTER);
            gameOverLabel.setAlignment(Pos.CENTER);

            Label scoreLabel = new Label("Your total score is:");
            scoreLabel.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 20));
            scoreLabel.setTextFill(Color.WHITE);
            scoreLabel.setBackground(transparentBackground);
            scoreLabel.setEffect(dropShadow);
            scoreLabel.setTextAlignment(TextAlignment.CENTER);
            scoreLabel.setAlignment(Pos.CENTER);

            Label scoreDisplayLabel = new Label(""+ score + "");
            scoreDisplayLabel.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 15));
            scoreDisplayLabel.setTextFill(Color.WHITE);
            scoreDisplayLabel.setBackground(transparentBackground);
            scoreDisplayLabel.setEffect(dropShadow);
            scoreDisplayLabel.setTextAlignment(TextAlignment.CENTER);
            scoreDisplayLabel.setAlignment(Pos.CENTER);

            Button viewStatsButton = new Button("View Statistics");
            viewStatsButton.setOnAction(e -> {
                    Stage stage = (Stage) viewStatsButton.getScene().getWindow();
            primaryStage.setScene(statGUI.createScene(stage));
            primaryStage.setFullScreen(true);
        });
            viewStatsButton.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 15));
            viewStatsButton.setTextFill(Color.WHITE);
            viewStatsButton.setBackground(orangeBackground);
            viewStatsButton.setEffect(dropShadow);
            viewStatsButton.setTextAlignment(TextAlignment.CENTER);
            viewStatsButton.setAlignment(Pos.CENTER);
            Button returnToMainButton = new Button("Return to Main Menu");
            returnToMainButton.setOnAction(e -> {
                Stage stage = (Stage) returnToMainButton.getScene().getWindow();
            primaryStage.setScene(mainmenuScene.createScene(stage));
            primaryStage.setFullScreen(true);
        });
            returnToMainButton.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 15));
            returnToMainButton.setTextFill(Color.WHITE);
            returnToMainButton.setBackground(orangeBackground);
            returnToMainButton.setEffect(dropShadow);
            returnToMainButton.setTextAlignment(TextAlignment.CENTER);
            returnToMainButton.setAlignment(Pos.CENTER);
            Insets offset = new Insets(10,10,10,10);
            
            VBox PostGameLay = new VBox();
            PostGameLay.setStyle("-fx-background-color: #FFD966;");
            PostGameLay.setAlignment(Pos.CENTER);
            PostGameLay.getChildren().addAll(gameOverLabel, scoreLabel, scoreDisplayLabel, returnToMainButton, viewStatsButton );
            PostGameLay.setSpacing(10);

            // Add the button to the scene's layout
            VBox root = (VBox) this.getRoot();
            root.getChildren().addAll(PostGameLay);
        }

        public static Scene createScene(Stage primarystage, int score) {
            return new postgameScene(primarystage, score);
        }
}
