package com.renanliberato.markdowneditor.editor;

import com.google.common.base.Joiner;
import com.google.common.io.Files;
import com.renanliberato.markdowneditor.Navigator;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.ResourceBundle;

public class EditorController implements Initializable {

    private Parser parser = Parser.builder().build();

    private HtmlRenderer renderer = HtmlRenderer.builder().build();

    private File currentFile;

    private boolean saved = true;

    @FXML
    Button newButton, openButton, saveButton;

    @FXML
    TextArea editorTextArea;

    @FXML
    WebView previewWebview;

    public void initialize(URL location, ResourceBundle resources) {
        editorTextArea.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            saved = false;
            Node document = parser.parse(newValue);

            previewWebview.getEngine().loadContent(renderer.render(document));
        });

        newButton.setOnMouseClicked((MouseEvent event) -> {
            if (!saved) {
                saveButton.fire();
            }

            currentFile = null;
            editorTextArea.textProperty().setValue("");
        });

        openButton.setOnMouseClicked((MouseEvent event) -> {
            if (!saved) {
                saveButton.fire();
            }

            FileChooser chooser = new FileChooser();

            chooser.setTitle("Choose your file");
            currentFile = chooser.showOpenDialog(Navigator.getStage());

            if (currentFile != null) {
                try {
                    List<String> lines = Files.readLines(currentFile, Charset.forName("UTF-8"));

                    String text = Joiner.on("\n").join(lines);

                    editorTextArea.textProperty().setValue(text);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        saveButton.setOnMouseClicked((MouseEvent event) -> {
            if (currentFile == null) {
                FileChooser chooser = new FileChooser();

                chooser.setTitle("Choose your destination");
                currentFile = chooser.showSaveDialog(Navigator.getStage());
            }

            if (currentFile != null) {
                try {
                    Files.write(editorTextArea.textProperty().getValue().getBytes(Charset.forName("UTF-8")), currentFile);
                    saved = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
