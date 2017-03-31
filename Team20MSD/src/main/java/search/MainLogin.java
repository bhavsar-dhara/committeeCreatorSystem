package main.java.search;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainLogin extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("/search/Search.fxml"));
    	Parent root = FXMLLoader.load(getClass().getResource("/main/java/search/Search.fxml"));
        primaryStage.setScene(new Scene(root, 600, 650));
        primaryStage.show();
    }

}
