package main.search;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Candidate extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("/search/Search.fxml"));
    	Parent root = FXMLLoader.load(getClass().getResource("/main/search/Search.fxml"));
        primaryStage.setScene(new Scene(root, 600, 650));
        primaryStage.show();
    }

}
