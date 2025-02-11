package edu.bsu.cs.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new Label("Foo"));
        stage.setScene(scene);
        stage.show();
    }
}
