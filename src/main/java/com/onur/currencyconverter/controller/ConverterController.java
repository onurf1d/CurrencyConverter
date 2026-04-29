package com.onur.currencyconverter.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import com.onur.currencyconverter.logic.ConverterLogic;
import com.onur.currencyconverter.model.CurrencyType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;


public class ConverterController implements Initializable {

    @FXML private TextField txtFrom;

    @FXML private TextField txtTo;

    @FXML private ComboBox<CurrencyType> fromCombo;

    @FXML private ComboBox<CurrencyType> toCombo;

    ConverterLogic cl = new ConverterLogic();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<CurrencyType> currencyList = FXCollections.observableArrayList(CurrencyType.values());

        fromCombo.setItems(currencyList);
        toCombo.setItems(currencyList);

        Callback<ListView<CurrencyType>, ListCell<CurrencyType>> cellFactory = lv -> new ListCell<>() {
            @Override
            protected void updateItem(CurrencyType item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    try {
                        ImageView flagView = new ImageView(new Image(getClass().getResourceAsStream(item.getFlagPath())));
                        flagView.setFitWidth(15);
                        flagView.setFitHeight(15);

                        setText(item.name() + " (" + item.getSymbol() + ")");
                        setGraphic(flagView);
                        setGraphicTextGap(10);
                    } catch (Exception e) {
                        setText(item.name());
                        setGraphic(null);
                    }
                }
            }
        };
        fromCombo.setCellFactory(cellFactory);
        fromCombo.setButtonCell(cellFactory.call(null));

        toCombo.setCellFactory(cellFactory);
        toCombo.setButtonCell(cellFactory.call(null));

        fromCombo.setValue(CurrencyType.EUR);
        toCombo.setValue(CurrencyType.USD);

        fromCombo.getSelectionModel().selectedItemProperty().addListener((obs, old, val) -> calculate());
        toCombo.getSelectionModel().selectedItemProperty().addListener((obs, old, val) -> calculate());

        txtFrom.textProperty().addListener((obs, old, val) -> calculate());
        txtTo.textProperty().addListener((obs, old, val) -> calculate());

        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*([\\.,]\\d*)?")) {
                return change;
            }
            return null;
        };

        txtFrom.setTextFormatter(new TextFormatter<>(filter));
        txtTo.setTextFormatter(new TextFormatter<>(filter));
    }

    @FXML
    private void calculate() {
        String from = fromCombo.getValue().name();
        String to = toCombo.getValue().name();

        if(txtFrom.isFocused()) {
            try {
                String input = txtFrom.getText().trim();
                if (input.isEmpty()) {
                    txtTo.setText("");
                    return;
                }
//                if(from.equals(to)) {
//                    txtTo.setText(txtFrom.getText());
//                }

                BigDecimal amount = new BigDecimal(input);

                String result = cl.convert(from, to, amount).toString();

                txtTo.setText(result);
            }catch(NumberFormatException e) {
                System.out.println("Invalid Number Format!");
            }
        }

        else if(txtTo.isFocused()) {
            try {
                String input = txtTo.getText().trim();
                if(input.isEmpty()) {
                    txtFrom.setText("");
                    return;
                }
                BigDecimal amount = new BigDecimal(input);

                String result = cl.convert(to, from, amount).toString();
                txtFrom.setText(result);
            }catch(NumberFormatException e) {
                System.out.println("Invalid Number Format!");
            }
        }

    }
}
