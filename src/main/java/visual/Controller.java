package visual;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Example;
import model.Flask;

import java.util.List;
import java.util.Random;

public class Controller {

    @FXML
    public Button calcButton;

    @FXML
    public PieChart pieChart;

    @FXML
    public TableView<Example> startPool;

    @FXML
    public TableColumn<String, Example> columnName;

    @FXML
    public TableColumn<String, Example> columnSequence;

    @FXML
    public TableColumn<String, Example> columnFitness;

    @FXML
    public TableView<Example> nextPool;

    @FXML
    public TableColumn<String, Example> columnName1;

    @FXML
    public TableColumn<String, Example> columnSequence1;

    @FXML
    public TableColumn<String, Example> columnFitness1;

    public void calcClick(ActionEvent actionEvent) {
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList();

        startPool.getItems().clear();
        nextPool.getItems().clear();

        Random rnd = new Random();
        Flask<Example> flask = new Flask<Example>();
        for (int i=0; i<8; i++) {
            Example e = new Example(rnd.nextInt(4096));
            flask.addItem(e);
            startPool.getItems().add(e);
        }

        for (int i=0; i<8; i++) {
            String percent = String.format("%.2f", flask.getPercents(i)*100);
            String caption =  flask.getItem(i).getName() + "(" + percent + "%)";
            pieChartData.add(new PieChart.Data(caption, flask.getItem(i).getFitness()));
        }

        List<Example> next = flask.crossParentPool(flask.getParentPool(4));
        for (Example e: next) {
            nextPool.getItems().add(e);
        }


        pieChart.setData(pieChartData);
        /*final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");
        double total = .0;
        for (PieChart.Data d : pieChart.getData()) {
            total += d.getPieValue();
        }
        caption.setTranslateX(e.getSceneX());
        caption.setTranslateY(e.getSceneY());
        String text = String.format("%.1f%%", 100*data.getPieValue()/total) ;
        caption.setText(text);*/

        pieChart.setLegendSide(Side.RIGHT);
        pieChart.setClockwise(true);
        pieChart.setStartAngle(90);

        columnName.setCellValueFactory(new PropertyValueFactory<String, Example>("name"));
        columnSequence.setCellValueFactory(new PropertyValueFactory<String, Example>("stringSquence"));
        columnFitness.setCellValueFactory(new PropertyValueFactory<String, Example>("fitness"));
        columnSequence.getStyleClass().add("centerCol");
        columnFitness.getStyleClass().add("centerCol");

        columnName1.setCellValueFactory(new PropertyValueFactory<String, Example>("name"));
        columnSequence1.setCellValueFactory(new PropertyValueFactory<String, Example>("stringSquence"));
        columnFitness1.setCellValueFactory(new PropertyValueFactory<String, Example>("fitness"));
        columnSequence1.getStyleClass().add("centerCol");
        columnFitness1.getStyleClass().add("centerCol");


    }
}
