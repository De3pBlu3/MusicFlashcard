import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Arrays;

public class FXMLcardBrowser {

    public TableView cardTable;
    public Label promptLabel;
    public GridPane GRIDpane;
    public Label questionContentlabel;
    public Label questionAnsLabel;
    public Label difficultyLabel;
    public Label categoryLabel;
    public Button backButton;


    @FXML
    private TableColumn<card, String> cardidColumn;
    @FXML
    private TableColumn<card, String> categoryColumn;
    @FXML
    private TableColumn<card, String> difficultyColumn;

    

    public void initialize() {


        cardidColumn.setCellValueFactory(
                new PropertyValueFactory<>("card_id"));

        categoryColumn.setCellValueFactory(
                new PropertyValueFactory<>("question_category"));

        difficultyColumn.setCellValueFactory(
                new PropertyValueFactory<>("question_difficulty"));





//         get all cards from DB
         card[] cards = DB_CardInteract.allCardsIncreasingDifficulty();

//         add cards to table

        for (card card : cards) {
            cardTable.getItems().add(card);
        }



        cardTable.setOnMouseClicked(e -> {
            // remove the line chart from the previous layout if it exists
            GRIDpane.getChildren().removeIf(node -> node instanceof Label);
            // get the selected row
            System.out.println("clicked on " + cardTable.getSelectionModel().getSelectedItem());
            // get the selected row c
            card selectedRow = (card) cardTable.getSelectionModel().getSelectedItem();
            // get the user id of the selected row
            String questionContent = selectedRow.getQuestion_content();
            String[] questionAnswersArr = selectedRow.getQuestion_answers_arr();
            int questionCorrectAnswer = selectedRow.getQuestion_correct_answer();
            int questionCategory = selectedRow.getQuestion_category();
            int questionDifficulty = selectedRow.getQuestion_difficulty();

            questionContentlabel.setText(questionContent);
            questionAnsLabel.setText(Arrays.toString(questionAnswersArr));
            difficultyLabel.setText("Difficulty: " + questionDifficulty);
            categoryLabel.setText("Category: " + questionCategory);





        });

        backButton.setOnAction(e -> {
            try {
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.setScene(FXMLloader.loadScene("FXML/MainMenu.fxml"));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });


    }
}
