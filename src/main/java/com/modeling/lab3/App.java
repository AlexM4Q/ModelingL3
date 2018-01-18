package com.modeling.lab3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class App extends Application {

    /**
     * Точка входа в программу
     *
     * @param args аргументы программы
     */
    public static void main(final String[] args) {
        launch(App.class, args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        final Parent root = FXMLLoader.load(getClass().getResource("/components/main/layout_main.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
