package com.example.web;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class HelloController {
    @FXML
    private WebView webView;

    @FXML
    private ImageView backButton;

    @FXML
    private ImageView forwardButton;
    @FXML
    private TextField addressBar;

    @FXML
    private void initialize() {
        WebEngine engine = webView.getEngine();
        engine.load("https://www.google.com");
        backButton.setOnMouseClicked(event -> goBack());
        forwardButton.setOnMouseClicked(event -> goForward());
    }


    @FXML
    private void goBack() {
        webView.getEngine().executeScript("history.back()");
    }

    @FXML
    private void goForward() {
        webView.getEngine().executeScript("history.forward()");
    }

    @FXML
    private void goToPage() {
        String url = addressBar.getText().trim();
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            if (!url.startsWith("www.")) {
                url = "www." + url;
            }
            url = "https://" + url;
        }
        webView.getEngine().load(url);
    }
    @FXML
    private void handleEnterKeyPressed(ActionEvent event) {
        goToPage();
    }
}
