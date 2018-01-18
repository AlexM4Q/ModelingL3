package com.modeling.lab3.entities;

public final class LemerGenerator extends Generator {

    private static final int A = 25173;
    private static final int B = 13849;
    private static final int C = 65536;
    private static final int X0 = 13;

    /**
     * Конструктор генератора пвсевдослучайных чисел методом Лемера
     *
     * @param length длина последовательности
     */
    public LemerGenerator(final int length) {
        super(length);
    }

    /**
     * Вычисление последовательности псевдослучайных чисел
     * с помощью метода Лемера
     *
     * @param length длинна последовательности
     * @return последовательность псевдослучаных чисел
     */
    @Override
    protected int[] calculate(final int length) {
        final int[] sequence = new int[length];

        sequence[0] = X0;
        for (int i = 1; i < length; i++) {
            sequence[i] = (A * sequence[i - 1] + B) % C;
        }

        for (int i = 0; i < sequence.length; i++) {
            sequence[i] = (int) ((double) UPPER_BOUND * sequence[i] / (C - 1));
        }

        return sequence;
    }

}
