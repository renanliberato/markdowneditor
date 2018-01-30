package com.renanliberato.markdowneditor.editor;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebView;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.net.URL;
import java.util.ResourceBundle;

public class EditorController implements Initializable {

    private Parser parser = Parser.builder().build();

    private HtmlRenderer renderer = HtmlRenderer.builder().build();

    @FXML
    TextArea editorTextArea;

    @FXML
    WebView previewWebview;

    public void initialize(URL location, ResourceBundle resources) {
        editorTextArea.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            Node document = parser.parse(newValue);

            previewWebview.getEngine().loadContent(renderer.render(document));
        });
    }
}
