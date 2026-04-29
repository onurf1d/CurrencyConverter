package com.onur.currencyconverter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application{

    @Override
    public void start(Stage stage) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/onur/currencyconverter/controller/Scene1.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/view/app.css").toExternalForm());

            stage.setScene(scene);
            stage.setTitle("Currency Converter");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/view/icon.png")));
            stage.setResizable(false);
            stage.setHeight(250);
            stage.setWidth(480);

            stage.show();

        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("Error");
        }
    }


    static void main(String[] args) {
            launch(args);
        }
    }
