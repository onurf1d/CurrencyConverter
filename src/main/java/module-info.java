module com.onur.currencyconverter {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.net.http;
    requires java.desktop;

    // 1. JavaFX'in FXML üzerinden Controller'a erişebilmesi için (Reflection için açar)
    opens com.onur.currencyconverter.controller to javafx.fxml;

    // 2. Eğer başka modüller de bu sınıfa erişecekse (Garanti olsun diye dışa aktarır)
    exports com.onur.currencyconverter.controller;

    // Ana paketini de açmayı unutma (Eğer Main sınıfın oradaysa)
    opens com.onur.currencyconverter to javafx.fxml;
    exports com.onur.currencyconverter;
}