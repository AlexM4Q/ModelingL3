package com.modeling.lab3.entities;

import java.util.Random;

public final class DefaultGenerator extends Generator {

    /**
     * Конструктор стандартного(встроенного) генератора пвсевдослучайных чисел
     *
     * @param length длина последовательности
     */
    public DefaultGenerator(final int length) {
        super(length);
    }

    /**
     * Вычисление последовательности псевдослучайных чисел
     * с помощью стандартного(встроенного) генератора пвсевдослучайных чисел
     *
     * @param length длинна последовательности
     * @return последовательность псевдослучаных чисел
     */
    @Override
    protected int[] calculate(final int length) {
        final int[] sequence = new int[length];

        final Random random = new Random();
        for (int i = 0; i < length; i++) {
            sequence[i] = random.nextInt(UPPER_BOUND);
        }

        return sequence;
    }

}
