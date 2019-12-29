package visual;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Example;
import model.Flask;

import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private XYChart.Series<Number, Number> series;
    private ObservableList<PieChart.Data> pieChartData
            = FXCollections.observableArrayList();

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

    @FXML
    public TableView<Example> parentPool;

    @FXML
    public TableColumn<String, Example> columnName2;

    @FXML
    public LineChart<Number, Number> lineChart;

    Flask<Example> flask;

    public void calcClick() {

        Random rnd = new Random();
        flask = new Flask<Example>();
        for (int i=0; i<8; i++) {
            Example e = new Example(rnd.nextInt(4096));
            flask.addItem(e);
            startPool.getItems().add(e);
        }

        renderData();
    }

    private void renderData() {
        startPool.getItems().clear();
        nextPool.getItems().clear();
        parentPool.getItems().clear();

        pieChartData.clear();

        for (int i=0; i<8; i++) {
            String percent = String.format("%05.2f", flask.getPercents(i)*100);
            String caption =  flask.getItem(i).getName() + "\t(" + percent + "%)";
            pieChartData.add(new PieChart.Data(caption, flask.getItem(i).getFitness()));
        }

        List<Example> items = flask.getItems();
        for (Example e: items) {
            startPool.getItems().add(e);
        }

        List<Example> parent = flask.getParents();
        if (parent != null)
        for (Example e: parent) {
            parentPool.getItems().add(e);
        }

//        List<Example> next = flask.crossParentPool(flask.getParentPool(4));
        List<Example> next = flask.getNextGeneration();
        if (next != null)
        for (Example e: next) {
            nextPool.getItems().add(e);
        }

        lineChart.setTitle("Age "+flask.getAge() + ", ave: " + flask.getAverageFitness());
    }

    public void nextClick() {
        series.getData().add(new XYChart.Data(flask.getAge(), flask.getAverageFitness()));
        flask.nextGeneration();
        renderData();
    }

    public void initialize(URL location, ResourceBundle resources) {
        pieChart.setLegendSide(Side.RIGHT);
        pieChart.setClockwise(true);
        pieChart.setStartAngle(90);

        series = new XYChart.Series();
        lineChart.getData().add(series);

        pieChart.setData(pieChartData);

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

        columnName2.setCellValueFactory(new PropertyValueFactory<String, Example>("name"));
    }
}
