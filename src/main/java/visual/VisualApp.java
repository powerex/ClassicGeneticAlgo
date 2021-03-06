package visual;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VisualApp extends Application {
    public void start(Stage primaryStage) throws Exception {
        String fxmlFile = "../fxml/main.fxml";

        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource(fxmlFile));
        root.getStylesheets().add(getClass().getResource("../style/table.css").toExternalForm());
        primaryStage.setTitle("Genetic algorithm");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
