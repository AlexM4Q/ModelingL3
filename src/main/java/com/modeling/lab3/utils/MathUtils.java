package com.modeling.lab3.utils;

public final class MathUtils {

    /**
     * Генерация приближенного значения Pi
     * через ряд Лейбница 1 - 1/3 + 1/5 - 1/7 ... = pi/4
     *
     * @param depth глубина просчета Pi
     * @return приближенное значение Pi
     */
    public static double generatePi(final int depth) {
        double pi = 1;
        for (int i = 1; i < depth; i++) {
            final double append = 1 / ((double) i * 2 + 1);
            if (i % 2 == 0) {
                pi += append;
            } else {
                pi -= append;
            }
        }
        return pi * 4;
    }

}