package edu.bsu.cs.GUI;

import edu.bsu.cs.RevisionFormatter;
import edu.bsu.cs.RevisionInputStream;
import edu.bsu.cs.RevisionParser;
import edu.bsu.cs.WikiConnection;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.io.InputStream;

public class Main extends Application {
    private final WikiConnection wikipediaConnection = new WikiConnection();
    private RevisionParser parser;
    private final RevisionFormatter revisionFormatter = new RevisionFormatter();
    @Override
    public void start(Stage stage) throws Exception {

        VBox parent = new VBox();

        HBox titleContainer = new HBox();
        Label title = new Label("Wikipedia Revision");
        title.setFont(new Font("Arial",35));
        titleContainer.getChildren().add(title);
        titleContainer.setAlignment(Pos.CENTER);
        titleContainer.setPadding(new Insets(0,0,100,0));
        parent.getChildren().add(titleContainer);

        Label articleLabel = new Label("Enter Article Title: ");
        HBox articleTitleInput = new HBox(articleLabel);
        TextField textField = new TextField();
        articleTitleInput.getChildren().add(textField);
        articleTitleInput.setAlignment(Pos.CENTER);
        articleTitleInput.setPadding(new Insets(0,0,50,0));
        parent.getChildren().add(articleTitleInput);

        VBox buttonContainer = new VBox();
        Button getRevisionsButton = new Button("Get revisions!");
        getRevisionsButton.setOnAction(actionEvent -> switchScene(stage,textField.getText()));
        buttonContainer.getChildren().add(getRevisionsButton);
        buttonContainer.setAlignment(Pos.CENTER);
        parent.getChildren().add(buttonContainer);

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setWidth(600);
        stage.setHeight(500);
        stage.show();
    }

    private void switchScene(Stage stage,String searchInput){
        InputStream wikiResponse = wikipediaConnection.search(searchInput);
        RevisionInputStream inputStream = new RevisionInputStream(wikiResponse);
        parser = new RevisionParser(inputStream);
        String output = revisionFormatter.printRevisionList(parser.parse());

        VBox parent = new VBox();

        HBox titleContainer = new HBox();
        Label title = new Label("Wikipedia Revision");
        title.setFont(new Font("Arial",35));
        titleContainer.getChildren().add(title);
        titleContainer.setAlignment(Pos.CENTER);
        titleContainer.setPadding(new Insets(0,0,20,0));
        parent.getChildren().add(titleContainer);

        parent.getChildren().add(new Label(parser.extractRedirect(inputStream.openInputStream())));
        parent.getChildren().add(new Label(output));

        stage.setScene(new Scene(parent));
    }
}
