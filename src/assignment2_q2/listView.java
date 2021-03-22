/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2_q2;


import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.Scanner;


/**
 *
 * @author pc
 */

public class listView extends Stage {

    private MenuBar menuBar;
    private Menu menuFile;
    private Menu menuColor;
    private Label menuAbout;
    private MenuItem menuItemOpen;
    private MenuItem menuItemClose;
    private MenuItem menuItemSave;
    private MenuItem menuItemExit;

    private MenuItem menuItemGold;
    private MenuItem menuItemCyan;
    private MenuItem menuItemRed;

    private TextArea textArea;
    private Slider sliderFontSize;
    private File selectedFile;

    public listView() {

        menuBar = new MenuBar();

        // File Menu
        menuFile = new Menu("File");
        menuItemOpen = new MenuItem("Open");
        menuItemClose = new MenuItem("Close");
        menuItemSave = new MenuItem("Save");
        menuItemExit = new MenuItem("Exit");
        menuFile.getItems().addAll(menuItemOpen, menuItemClose, menuItemSave, menuItemExit);

        // Color Menu
        menuColor = new Menu("Color");
        menuItemGold = new MenuItem("Gold");
        menuItemCyan = new MenuItem("Cyan");
        menuItemRed = new MenuItem("Red");
        menuColor.getItems().addAll(menuItemGold, menuItemCyan, menuItemRed);

        // About menu
        menuAbout = new Label("About");

        menuAbout.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Label name = new Label("Mahmoud N Abo-Nima");
                Label id = new Label("120181067");
                Label course = new Label("Programming 3");

                VBox vBox = new VBox(10, name, id, course);
                vBox.setAlignment(Pos.CENTER);

                FlowPane flowPane = new FlowPane(vBox);
                flowPane.setAlignment(Pos.CENTER);

                Scene scene = new Scene(flowPane, 150, 150);

                Stage myDialog = new Stage();
                myDialog.setScene(scene);
                myDialog.show();
            }
        });

        Menu fileMenuButton = new Menu();
        fileMenuButton.setGraphic(menuAbout);



        // Add to menu
        menuBar.getMenus().addAll(menuFile, menuColor, fileMenuButton);

        textArea = new TextArea("Playing with JavaFx Advanced Controls");
        textArea.setMinHeight(160);

        sliderFontSize = new Slider(5, 35, 12);
        sliderFontSize.setMajorTickUnit(5);
        sliderFontSize.setMinorTickCount(4);
        sliderFontSize.setShowTickLabels(true);
        sliderFontSize.setShowTickMarks(true);
        sliderFontSize.setSnapToTicks(true);

        // Events on file menu
        sliderFontSize.valueProperty().addListener(event -> {
            textArea.setStyle("-fx-font-size:" + sliderFontSize.getValue() + "pt");
        });

        menuItemOpen.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("src/assignment2_q2"));
            selectedFile = fileChooser.showOpenDialog(this);
            textArea.appendText("\n");

            try {

                Scanner scanner = new Scanner(selectedFile);
                while (scanner.hasNext()) {
                    textArea.appendText(scanner.nextLine() + "\n");
                }
                scanner.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        });

        menuItemClose.setOnAction(event -> {
            textArea.setText("");
        });

        menuItemSave.setOnAction(event -> {

            try {

                FileWriter writer = new FileWriter(selectedFile);

                writer.write(textArea.getText());
                writer.flush();
                writer.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        menuItemExit.setOnAction(event -> {

            Stage stage = (Stage) getScene().getWindow();
            stage.close();

        });

        // Events on color menu
        menuItemGold.setOnAction(event -> {
            textArea.setStyle("-fx-control-inner-background: gold;");
        });

        menuItemCyan.setOnAction(event -> {
            textArea.setStyle("-fx-control-inner-background: cyan");
        });

        menuItemRed.setOnAction(event -> {
            textArea.setStyle("-fx-control-inner-background: red");
        });

        ComboBox<String> comboBoxLinks = new ComboBox<>();
        comboBoxLinks.getItems().addAll("Instructor GitHub", "JavaFx Guide", "Ma7moudNizar");

        // comboBoxLinks.getSelectionModel().selectFirst();

        WebView webView = new WebView();
        //webView.getEngine().load("https://www.youtube.com/watch?v=S_kk8hic8T4&list=PLz_YkiqIHessJiLMK_fve7ogj_XuenmdO&index=8");

        comboBoxLinks.setOnAction(event -> {
            String action = comboBoxLinks.getValue().toString();
            if (action.equalsIgnoreCase("Instructor GitHub")){

                webView.getEngine().load("https://github.com/aashgar");

            }else if (action.equalsIgnoreCase("JavaFx Guide")){

                webView.getEngine().load("https://www.tutorialspoint.com/javafx/index.htm");

            }else if (action.equalsIgnoreCase("Ma7moudNizar")){

                webView.getEngine().load("https://github.com/MahmoudNizar7");

            }
        });

        HBox hBox1 = new HBox(15, comboBoxLinks, webView);

        VBox vBox1 = new VBox(15, textArea, sliderFontSize, hBox1);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(vBox1);

        Scene scene = new Scene(borderPane, 1000, 600);
        setScene(scene);

        setTitle("Advanced JavaFX Screen");

    }

}
