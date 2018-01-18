package com.modeling.lab3.controllers;

import com.modeling.lab3.constants.GeneratorType;
import com.modeling.lab3.entities.Generator;
import com.modeling.lab3.utils.MathUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public final class MainController {

    /**
     * Поле ввода длины последовательности
     */
    @FXML
    private TextField length_L;
    /**
     * Строка вывода мат. ожидания
     */
    @FXML
    private Label average_L;
    /**
     * Строка вывода дисперсии
     */
    @FXML
    private Label dispersion_L;
    /**
     * Гистограмма относительной частоты появления чисел
     */
    @FXML
    private BarChart<String, Number> frequency_BC;
    /**
     * Гистограмма накопления относительной частоты появления чисел
     */
    @FXML
    private BarChart<String, Number> accumulation_BC;
    /**
     * Глубина просчета приблизительного значения Pi
     */
    @FXML
    private TextField piDepth_TF;
    /**
     * Строка вывода приблизительного значения Pi
     */
    @FXML
    private Label pi_L;
    /**
     * Выбранный тип генератора последовательности псевдослучайных чисел
     */
    private GeneratorType generatorType = GeneratorType.DEFAULT;

    /**
     * Вызыывается JavaFX после подготовки интерфейса,
     * но до визуализации для пользователя
     */
    @FXML
    public void initialize() {
        // Настройка поля ввода длины последовательности на ввод только целых положительных чисел
        length_L.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                length_L.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        updatePi(50);
        piDepth_TF.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                piDepth_TF.setText(newValue.replaceAll("[^\\d]", ""));
            }

            final String piDepthText = piDepth_TF.getText();
            if (!piDepthText.isEmpty()) {
                updatePi(Integer.valueOf(piDepthText));
            }
        });
    }

    private void updatePi(final int depth) {
        if (depth > 99999999) {
            pi_L.setText("Глубина должна быть меньше 99999999");
        } else {
            pi_L.setText(String.valueOf(MathUtils.generatePi(depth)));
        }
    }

    /**
     * Установка типа генератора - Стандартный
     */
    @FXML
    private void defaultGeneratorType_RB_action() {
        generatorType = GeneratorType.DEFAULT;
    }

    /**
     * Установка типа генератора - Лемер
     */
    @FXML
    private void lemerGeneratorType_RB_action() {
        generatorType = GeneratorType.LEMER;
    }

    /**
     * Старт генерации последовательности псевдослучайных чисел
     * с помощью выбранного генератора
     */
    @FXML
    private void start_B_action() {
        // Получение длины последовательности из поля ввода
        final int length = Integer.valueOf(length_L.getText());
        // Фиксирование длины последовательности до необходимого интервала [100; 10000]
        final Integer fixedLength = Math.min(Math.max(100, length), 10000);
        // Установка фиксированного значения в поле ввода
        length_L.setText(fixedLength.toString());

        // Создание выбранного генератора последовательности псевдослучаных чисел заданной длины
        // Во время инициализации генератора происходит вычисление всех необходимых значенй
        final Generator generator = generatorType.createGenerator(fixedLength);
        // Установка мат. ожидания в строку вывода
        average_L.setText(String.valueOf(generator.getAverage()));
        // Установка дисперсии в строку вывода
        dispersion_L.setText(String.valueOf(generator.getDispersion()));

        // Установка относителный частоты появления чисел в соответствующую гистограмму
        setValues(frequency_BC, generator.getFrequencyData());
        // Установка накопления относителной частоты появления чисел в соответствующую гистограмму
        setValues(accumulation_BC, generator.getAccumulationData());
    }

    /**
     * Очистка гистограм и полей вывода статистики
     */
    @FXML
    private void clear_B_action() {
        average_L.setText("");
        dispersion_L.setText("");
        frequency_BC.getData().clear();
        accumulation_BC.getData().clear();
    }

    /**
     * Выход из программы
     */
    @FXML
    private void exit_B_action() {
        System.exit(0);
    }

    /**
     * Установка данных в гистограмму
     *
     * @param barChart гистограмма
     * @param data     данные
     */
    private static void setValues(final BarChart<String, Number> barChart, final Number[] data) {
        // Создание серии гистограммы
        final ObservableList<XYChart.Data<String, Number>> seriesData = FXCollections.observableArrayList();
        // Заполнение серии пришедшими данными
        for (int i = 0; i < data.length; i++) {
            seriesData.add(new XYChart.Data<>(String.valueOf(i), data[i]));
        }

        // Получение списка серий гистограммы
        final ObservableList<XYChart.Series<String, Number>> barChartData = barChart.getData();
        // Очистка списка
        barChartData.clear();
        // Добавление новой серии с пришедшими данными
        barChartData.add(new XYChart.Series<>(seriesData));
    }

}
