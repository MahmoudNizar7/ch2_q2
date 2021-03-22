/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2_q2;

import javafx.application.Application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.util.Scanner;
import javafx.stage.Stage;

/**
 *
 * @author pc
 */
public class Assignment2_q2 extends Application {

    private Label labelTitleLabel, labelError;
    private TextField textFieldLoginName;
    private PasswordField PasswordField;
    private Button buttonSubmit, buttonCancel;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) {

        labelTitleLabel = new Label("Login information");
        textFieldLoginName = new TextField();
        textFieldLoginName.setPromptText("Login Name"); // placeholder
        PasswordField = new PasswordField();
        PasswordField.setPromptText("Password"); // placeholder
        labelError = new Label("Initial text");
        stage = primaryStage;

        VBox vBox1 = new VBox(10, labelTitleLabel, textFieldLoginName, PasswordField, labelError);
        vBox1.setAlignment(Pos.CENTER);
        vBox1.setStyle("-fx-border-color: red");

        buttonSubmit = new Button("Submit");
        buttonCancel = new Button("Cancel");

        buttonSubmit.setOnAction(new MyEventHandler());
        buttonCancel.setOnAction(new MyEventHandler());

        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(buttonSubmit, buttonCancel);
        hBox1.setSpacing(20);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setStyle("-fx-border-color: lime");

        VBox vBox2 = new VBox(10, vBox1, hBox1);
        vBox2.setAlignment(Pos.CENTER);
        vBox2.setStyle("-fx-border-color: navy");
        vBox2.setPadding(new Insets(10, 20, 10, 20));

        FlowPane flowPane = new FlowPane(vBox2);
        flowPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(flowPane, 400, 300);
        scene.getStylesheets().add("file:src/assignment2_q2/loginStyles.css");
        primaryStage.setScene(scene);

        primaryStage.setTitle("Login Screen");
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private class MyEventHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == buttonSubmit) {

                Scanner x = null;
                try {
                    x = new Scanner(new File("src/assignment2_q2/loginData.txt"));
                } catch (Exception e) {
                    System.out.println("Couldn't find file");
                    System.exit(0);
                }

                String temp;
                String[] info;

                while (x.hasNext()) {
                    temp = x.nextLine();
                    info = temp.split(" ");

                    if (info[0].equals(textFieldLoginName.getText()) && info[1].equals(PasswordField.getText())) {

                        new listView().show();
                        stage.close();

                        break;

                    } else {

                        textFieldLoginName.setText("");
                        PasswordField.setText("");
                        labelError.setText("Login failed wrong Name or Password");

                    }
                }

            } else if (event.getSource() == buttonCancel) {
                textFieldLoginName.setText("");
                PasswordField.setText("");
            }
        }
    }

}
