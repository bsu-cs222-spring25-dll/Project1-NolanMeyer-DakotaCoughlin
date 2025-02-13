package edu.bsu.cs.GUI;

import edu.bsu.cs.Exceptions.noArticleException;
import edu.bsu.cs.Exceptions.networkErrorException;
import edu.bsu.cs.Exceptions.openInputStreamException;
import edu.bsu.cs.RevisionFormatter;
import edu.bsu.cs.RevisionInputStream;
import edu.bsu.cs.RevisionParser;
import edu.bsu.cs.WikiConnection;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main extends Application {
    private final WikiConnection wikipediaConnection = new WikiConnection();
    private RevisionParser parser;
    private final RevisionFormatter revisionFormatter = new RevisionFormatter();
    @Override
    public void start(Stage stage) throws Exception {

        VBox parent = new VBox();

        createTitle(parent,100);

        Label articleLabel = new Label("Enter Article Title: ");
        HBox articleTitleInput = new HBox(articleLabel);
        TextField textField = new TextField();
        articleTitleInput.getChildren().add(textField);
        articleTitleInput.setAlignment(Pos.CENTER);
        articleTitleInput.setPadding(new Insets(0,0,50,0));
        parent.getChildren().add(articleTitleInput);

        VBox buttonContainer = new VBox();
        Button getRevisionsButton = new Button("Get revisions!");
        getRevisionsButton.setOnAction(actionEvent -> {
            try {
                switchScene(stage,textField.getText());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        buttonContainer.getChildren().add(getRevisionsButton);
        buttonContainer.setAlignment(Pos.CENTER);
        parent.getChildren().add(buttonContainer);

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setWidth(600);
        stage.setHeight(550);
        stage.show();
    }

    private void switchScene(Stage stage,String searchInput) throws FileNotFoundException {
        String output = "";
        VBox parent = new VBox();
        parent.setAlignment(Pos.CENTER);
        Alert errorPopUp = new Alert(Alert.AlertType.INFORMATION);
        errorPopUp.setTitle("Error!");

        createTitle(parent,20);

        if(searchInput.isEmpty()){
            errorPopUp.setContentText("Please enter an article!");
            errorPopUp.showAndWait();
        }else {

            try {
                InputStream wikiResponse = wikipediaConnection.search(searchInput);
                RevisionInputStream inputStream = new RevisionInputStream(wikiResponse);
                parser = new RevisionParser(inputStream);
                output = revisionFormatter.printRevisionList(parser.parse());

                Label redirectLabel = new Label(parser.extractRedirect(inputStream.openInputStream()));
                redirectLabel.setPadding(new Insets(0,0,10,0));
                parent.getChildren().add(redirectLabel);

            } catch (noArticleException | networkErrorException | openInputStreamException e) {
                errorPopUp.setContentText(e.getMessage());
                errorPopUp.showAndWait();
            }
        }

        Label outputLabel = new Label(output);
        outputLabel.setPadding(new Insets(0,0,10,180));
        parent.getChildren().add(outputLabel);

        HBox backButtonContainer = new HBox();
        Button backButton = new Button("<-Back");
        backButton.setOnAction(actionEvent -> {
            try {
                start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        backButtonContainer.getChildren().add(backButton);
        parent.getChildren().add(backButtonContainer);

        if(!output.isEmpty()) {
            stage.setScene(new Scene(parent));
        }
    }

    public void createTitle(VBox parent,int padding) throws FileNotFoundException {
        HBox titleContainer = new HBox();
        Label title = new Label("Wikipedia Revisions");
        title.setFont(new Font("Arial",35));
        titleContainer.getChildren().add(title);
        titleContainer.setAlignment(Pos.CENTER);
        Image wikiImage = new Image(new FileInputStream("src/main/resources/wikiLogo.png"));
        ImageView imageView = new ImageView(wikiImage);
        imageView.setFitHeight(60);
        imageView.setFitWidth(60);
        titleContainer.getChildren().add(imageView);
        titleContainer.setPadding(new Insets(0,0,padding,0));
        parent.getChildren().add(titleContainer);
    }
}
