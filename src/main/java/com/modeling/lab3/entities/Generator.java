package com.modeling.lab3.entities;

import java.util.HashMap;
import java.util.Map;

public abstract class Generator {

    protected static final int UPPER_BOUND = 100;

    protected double average;
    protected double dispersion;
    protected Number[] frequencyData;
    protected Number[] accumulationData;

    protected Generator(final int length) {
        final int[] sequence = calculate(length);

        final Map<Integer, Integer> variables = new HashMap<>();
        for (int var : sequence) {
            Integer integer = variables.get(var);
            integer = integer == null ? 1 : integer + 1;
            variables.put(var, integer);
        }

        for (Map.Entry<Integer, Integer> entry : variables.entrySet()) {
            average += (double) entry.getValue() * entry.getKey() / length;
        }

        for (Map.Entry<Integer, Integer> entry : variables.entrySet()) {
            final double diff = entry.getValue() - average;
            dispersion += diff * diff * entry.getKey() / length;
        }

        frequencyData = new Double[UPPER_BOUND];
        for (int i = 0; i < UPPER_BOUND; i++) {
            Integer integer = variables.get(i);
            frequencyData[i] = integer == null ? 0 : (double) integer / length;
        }

        accumulationData = new Double[UPPER_BOUND];
        for (int i = 0; i < UPPER_BOUND; i++) {
            final double previous = i == 0 ? 0 : accumulationData[i - 1].doubleValue();
            final double current = frequencyData[i].doubleValue();
            accumulationData[i] = previous + current;
        }
    }

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
