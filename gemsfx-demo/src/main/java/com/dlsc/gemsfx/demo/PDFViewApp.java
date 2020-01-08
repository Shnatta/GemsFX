package com.dlsc.gemsfx.demo;

import com.dlsc.gemsfx.PDFView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class PDFViewApp extends Application {

    private FileChooser chooser;

    @Override
    public void start(Stage primaryStage) {
        PDFView view = new PDFView();

        Button loadButton = new Button("Load PDF ...");
        loadButton.setMaxWidth(Double.MAX_VALUE);
        loadButton.setOnAction(evt -> {
            if (chooser == null) {
                chooser = new FileChooser();
                chooser.setTitle("Load PDF File");
                final ExtensionFilter filter = new ExtensionFilter("PDF Files", "*.pdf");
                chooser.getExtensionFilters().add(filter);
                chooser.setSelectedExtensionFilter(filter);
            }

            final File file = chooser.showOpenDialog(loadButton.getScene().getWindow());
            if (file != null) {
                try {
                    view.load(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        VBox.setVgrow(view, Priority.ALWAYS);
        VBox box = new VBox(20, view, loadButton);
        box.setFillWidth(true);

        StackPane stackPane = new StackPane(box);
        stackPane.setPadding(new Insets(20));

        Scene scene = new Scene(stackPane);
        primaryStage.setTitle("PDF View");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(900);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}