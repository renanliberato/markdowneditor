package com.renanliberato.markdowneditor;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class Navigator {

    private static Stage stage = null;

    public static void setStage(Stage newStage) {
        stage = newStage;
    }

    public static void navigateToEditor() {
        FXMLLoader loader = new FXMLLoader();
        try {
            Parent rootNode = loader.load(Navigator.class.getResourceAsStream("/fxml/editor/editor.fxml"));

            Scene scene = new Scene(rootNode, 400, 400);

            stage.setTitle("Edit your Markdown");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Stage getStage() {
        return stage;
    }
}
