package com.modeling.lab3.controllers;

import com.modeling.lab3.constants.GeneratorType;
import com.modeling.lab3.entities.Generator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public final class MainController {

    @FXML
    private TextField length_L;
    @FXML
    private Label average_L;
    @FXML
    private Label dispersion_L;
    @FXML
    private BarChart<String, Number> frequency_BC;
    @FXML
    private BarChart<String, Number> accumulation_BC;

    private GeneratorType generatorType = GeneratorType.DEFAULT;

    @FXML
    public void initialize() {
        length_L.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                length_L.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    @FXML
    private void defaultGeneratorType_RB_action() {
        generatorType = GeneratorType.DEFAULT;
    }

    @FXML
    private void lemerGeneratorType_RB_action() {
        generatorType = GeneratorType.LEMER;
    }

    @FXML
    private void start_B_action() {
        final int length = Integer.valueOf(length_L.getText());
        final Integer fixedLength = Math.min(Math.max(100, length), 10000);
        length_L.setText(fixedLength.toString());

        final Generator generator = generatorType.createGenerator(fixedLength);
        average_L.setText(String.valueOf(generator.getAverage()));
        dispersion_L.setText(String.valueOf(generator.getDispersion()));

        setValues(frequency_BC, generator.getFrequencyData());
        setValues(accumulation_BC, generator.getAccumulationData());
    }

    @FXML
    private void clear_B_action() {
        frequency_BC.getData().clear();
        accumulation_BC.getData().clear();
    }

    @FXML
    private void exit_B_action() {
        System.exit(0);
    }

    private static void setValues(final BarChart<String, Number> barChart, final Number[] data) {
        final ObservableList<XYChart.Data<String, Number>> seriesData = FXCollections.observableArrayList();
        for (int i = 0; i < data.length; i++) {
            seriesData.add(new XYChart.Data<>(String.valueOf(i), data[i]));
        }

        final ObservableList<XYChart.Series<String, Number>> barChartData = barChart.getData();
        barChartData.clear();
        barChartData.add(new XYChart.Series<>(seriesData));
    }

}
