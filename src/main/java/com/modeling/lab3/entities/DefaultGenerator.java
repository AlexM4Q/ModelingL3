package com.modeling.lab3.entities;

import java.util.Random;

public final class DefaultGenerator extends Generator {

    public DefaultGenerator(final int length) {
        super(length);
    }

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
