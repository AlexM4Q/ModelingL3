package com.modeling.lab3.entities;

import java.util.HashMap;
import java.util.Map;

public abstract class Generator {

    /**
     * Максимальное значение последовательности
     */
    protected static final int UPPER_BOUND = 100;

    /**
     * Математическое ожидание
     */
    protected double average;
    /**
     * Дисперсия
     */
    protected double dispersion;
    /**
     * Относительная частота появления чисел
     */
    protected Number[] frequencyData;
    /**
     * Накопление отностельной частоты появления чисел
     */
    protected Number[] accumulationData;

    /**
     * Базовый конструктор генератора
     *
     * @param length длина последовательности
     */
    protected Generator(final int length) {
        // сгенерированная последовательность псевдослучаных чисел
        final int[] sequence = calculate(length);

        // карта пар [число]-[количество повторений числа]
        final Map<Integer, Integer> variables = new HashMap<>();
        // поиск повторений чисел
        for (int var : sequence) {
            Integer integer = variables.get(var);
            integer = integer == null ? 1 : integer + 1;
            variables.put(var, integer);
        }

        // вычисление мат. ожидания
        for (Map.Entry<Integer, Integer> entry : variables.entrySet()) {
            average += (double) entry.getValue() * entry.getKey() / length;
        }

        // вычисление дисперсии
        for (Map.Entry<Integer, Integer> entry : variables.entrySet()) {
            final double diff = entry.getValue() - average;
            dispersion += diff * diff * entry.getKey() / length;
        }

        // вычисление относительной частоты появления чисел
        frequencyData = new Double[UPPER_BOUND];
        for (int i = 0; i < UPPER_BOUND; i++) {
            Integer integer = variables.get(i);
            frequencyData[i] = integer == null ? 0 : (double) integer / length;
        }

        // вычисление накопления отностельной частоты появления чисел
        accumulationData = new Double[UPPER_BOUND];
        for (int i = 0; i < UPPER_BOUND; i++) {
            final double previous = i == 0 ? 0 : accumulationData[i - 1].doubleValue();
            final double current = frequencyData[i].doubleValue();
            accumulationData[i] = previous + current;
        }
    }

    /**
     * Вычисление последовательности псевдослучайных чисел
     *
     * @param length длинна последовательности
     * @return последовательность псевдослучаных чисел
     */
    protected abstract int[] calculate(final int length);

    public double getAverage() {
        return average;
    }

    public double getDispersion() {
        return dispersion;
    }

    public Number[] getFrequencyData() {
        return frequencyData;
    }

    public Number[] getAccumulationData() {
        return accumulationData;
    }

}
