package com.renanliberato.markdowneditor;

import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        stage.setWidth(1020.0);
        stage.setHeight(600.0);
        Navigator.setStage(stage);

        Navigator.navigateToEditor();
    }
}
