import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLstatController {
    public TableColumn userNameCol;
    public TableColumn scoreCol;
    public TableView statsTable;
    public GridPane leaderboardLay;
    public GridPane graphLay;
    public LineChart<String, Number> lineChart_pop;
    public LineChart<String, Number> lineChart_player;
    public Tab graphButton;
    public Button backButton;

    @FXML
    public void initialize() {
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("user_ID"));
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score_of_round"));
        history[] all_history = Statistics.get_all_history();
        for (int i = 0; i < all_history.length; i++) {
            statsTable.getItems().add(all_history[i]);
        }

        backButton.setOnAction(e -> {
            Stage stage = (Stage) graphLay.getScene().getWindow();
            try {
                stage.setScene(FXMLloader.loadScene("FXML/MainMenu.fxml"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        graphButton.setOnSelectionChanged(e -> {
            if (graphButton.isSelected()) {
                // replace linechart with new linechart
                graphLay.getChildren().removeIf(node -> node instanceof LineChart);
                lineChart_pop = create_line_chart_population_history();
                graphLay.setConstraints(lineChart_pop, 1, 0);
                graphLay.getChildren().add(lineChart_pop);

                lineChart_player = create_line_chart_player_history(launcher.user_ID);
                graphLay.setConstraints(lineChart_player, 0, 0);
                graphLay.getChildren().add(lineChart_player);

            }
        });
        statsTable.setOnMouseClicked(e -> {
            // remove the line chart from the previous layout if it exists
            leaderboardLay.getChildren().removeIf(node -> node instanceof LineChart);
            // get the selected row
            System.out.println("clicked on " + statsTable.getSelectionModel().getSelectedItem());
            // get the selected row c
            history selectedRow = (history) statsTable.getSelectionModel().getSelectedItem();
            // get the user id of the selected row
            String row_user_id = selectedRow.getUser_ID();
            // create a line chart for that user
            LineChart<String, Number> lineChart = create_line_chart_player_history(row_user_id);
            // add the line chart to the layout
            leaderboardLay.setConstraints(lineChart, 1, 0);
            leaderboardLay.getChildren().add(lineChart);

        });
    }

    public static LineChart<String, Number> create_line_chart_player_history(String user_id) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Date");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Score");
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Score over time (Player)");
        history[] player_history = Statistics.get_player_history(user_id);
        XYChart.Series series = new XYChart.Series();
        series.setName("Date/Score");
        // line chart data
        for (int i = 0; i < player_history.length; i++) {
            series.getData().add(new XYChart.Data<>(player_history[i].getDate(), player_history[i].getScore_of_round()));
        }
        // line chart data
        lineChart.getData().add(series);
        return(lineChart);
    }


    public static LineChart<String, Number> create_line_chart_population_history() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Date");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Score");
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Score over time (population history)");
        history[] pop_history = Statistics.get_all_history();
        XYChart.Series series = new XYChart.Series();
        series.setName("Date/Score");
        // line chart data
        for (int i = 0; i < pop_history.length; i++) {
            series.getData().add(new XYChart.Data<>(pop_history[i].getDate(), pop_history[i].getScore_of_round()));
        }
        // line chart data
        lineChart.getData().add(series);
        return(lineChart);
    }

}
