module com.onur.currencyconverter {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.net.http;
    requires java.desktop;


    opens com.onur.currencyconverter.controller to javafx.fxml;


    exports com.onur.currencyconverter.controller;


    opens com.onur.currencyconverter to javafx.fxml;
    exports com.onur.currencyconverter;
}