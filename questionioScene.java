import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;


public class questionioScene extends Scene {

    public questionioScene(Stage primaryStage) {
        super(new VBox(), 440, 350);
        Background transparentBackground = new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
        Background orangeBackground = new Background(new BackgroundFill(Color.rgb(232, 123, 56), CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY));
        DropShadow dropShadow = new DropShadow();
   	 
        //questionIO controls
        Insets offset = new Insets(10,10,10,10);

        Image CUizLogo = new Image("CUiz Logo.png");
        ImageView CUizView = new ImageView();
        CUizView.setFitWidth(440);
        CUizView.setFitHeight(250);
        CUizView.setImage(CUizLogo);

        TextField questionInTextfield= new TextField();
        questionInTextfield.setPromptText("Input Question");
        questionInTextfield.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 20));
        questionInTextfield.setEffect(dropShadow);
        questionInTextfield.setAlignment(Pos.CENTER);

        TextField rightAnswerInTextfield= new TextField();
        rightAnswerInTextfield.setPromptText("Input Correct Answer");
        rightAnswerInTextfield.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 13));
        rightAnswerInTextfield.setEffect(dropShadow);
        rightAnswerInTextfield.setAlignment(Pos.CENTER);

        TextField wrongAnswer1InTextfield= new TextField();
        wrongAnswer1InTextfield.setPromptText("Input Wrong Answer 1");
        wrongAnswer1InTextfield.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 13));
        wrongAnswer1InTextfield.setEffect(dropShadow);
        wrongAnswer1InTextfield.setAlignment(Pos.CENTER);

        TextField wrongAnswer2InTextfield= new TextField();
        wrongAnswer2InTextfield.setPromptText("Input Wrong Answer 2");
        wrongAnswer2InTextfield.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 13));
        wrongAnswer2InTextfield.setEffect(dropShadow);
        wrongAnswer2InTextfield.setAlignment(Pos.CENTER);

        TextField wrongAnswer3InTextfield= new TextField();
        wrongAnswer3InTextfield.setPromptText("Input Wrong Answer 3");
        wrongAnswer3InTextfield.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 13));
        wrongAnswer3InTextfield.setEffect(dropShadow);
        wrongAnswer3InTextfield.setAlignment(Pos.CENTER);

        Button returnMainButton = new Button("Return to Main Menu");
        returnMainButton.setOnAction(e -> {
        Stage stage = (Stage) returnMainButton.getScene().getWindow();
        primaryStage.setScene(mainmenuScene.createScene(stage));
        });
        returnMainButton.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 13));
        returnMainButton.setTextFill(Color.WHITE);
        returnMainButton.setBackground(transparentBackground);
        returnMainButton.setEffect(dropShadow);
        returnMainButton.setTextAlignment(TextAlignment.CENTER);
        returnMainButton.setAlignment(Pos.CENTER);

        Button doneButton = new Button("Done");//******************************
        doneButton.setOnAction(e -> {
            // generate string USR+random number
            // add card to database
            int rand = (int)(Math.random() * 500)+1;
            // string
            String card_id= "usr" + rand;

            card user_manual = new card(card_id, questionInTextfield.getText(), rightAnswerInTextfield.getText() + ";" + wrongAnswer1InTextfield.getText() + ";" + wrongAnswer2InTextfield.getText() + ";" + wrongAnswer3InTextfield.getText(), 0, 0, 0);
            DB_CardInteract.addCardToDB(user_manual);
            Popup successfulQAddp = new Popup();
            Label successfulQAddpLabel = new Label("Question Successfully added");
            successfulQAddp.getContent().add(successfulQAddpLabel);
            SuccessPopUp(successfulQAddp, primaryStage);
        });
        doneButton.setFont(Font.font("ADLam Display", FontWeight.NORMAL, 13));
        doneButton.setTextFill(Color.WHITE);
        doneButton.setBackground(transparentBackground);
        doneButton.setEffect(dropShadow);
        doneButton.setTextAlignment(TextAlignment.CENTER);
        doneButton.setAlignment(Pos.CENTER);
        
        ImageView CUizView2 = new ImageView();
        CUizView2.setImage(CUizLogo);


        VBox CenterQuestionIOLay = new VBox(10);
        CenterQuestionIOLay.setAlignment(Pos.CENTER);
        CenterQuestionIOLay.getChildren().addAll(questionInTextfield, rightAnswerInTextfield, wrongAnswer1InTextfield, wrongAnswer2InTextfield, wrongAnswer3InTextfield, doneButton);


        BorderPane QuestionIOLay = new BorderPane();
        QuestionIOLay.setStyle("-fx-background-color: #FFD966;");
        QuestionIOLay.setPadding(offset);
        QuestionIOLay.setTop(CUizView2);
        QuestionIOLay.setAlignment(CUizView2, Pos.CENTER);
        QuestionIOLay.setCenter(CenterQuestionIOLay);
        QuestionIOLay.setBottom(returnMainButton);




        // Add the button to the scene's layout
        VBox root = (VBox) this.getRoot();
        root.getChildren().addAll(QuestionIOLay);

    }
    public static questionioScene createScene(Stage primaryStage) {
        return new questionioScene(primaryStage);
    }

    public static void SuccessPopUp(Popup successfulQAddp, Stage stage) {
        Timeline popupTimeline = new Timeline( //To store keyframes
                new KeyFrame(Duration.seconds(0), e -> {
                    successfulQAddp.show(stage);
                }),
                new KeyFrame(Duration.seconds(3), e -> {
                    successfulQAddp.hide();
                }));
        popupTimeline.setCycleCount(1); //How many times it plays the sequence
        popupTimeline.playFromStart();
    }

}


