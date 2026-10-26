package com.camila.biblioapp;

import com.camila.biblioapp.view.HomeView;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        HomeView home_view = new HomeView();
        home_view.home(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
