package com.modeling.lab3.constants;

import com.modeling.lab3.entities.DefaultGenerator;
import com.modeling.lab3.entities.Generator;
import com.modeling.lab3.entities.LemerGenerator;

import java.util.function.Function;

public enum GeneratorType {

    DEFAULT(DefaultGenerator::new),
    LEMER(LemerGenerator::new);

    private final Function<Integer, Generator> generatorCreator;

    GeneratorType(final Function<Integer, Generator> generatorCreator) {
        this.generatorCreator = generatorCreator;
    }

    public Generator createGenerator(final int length) {
        return generatorCreator.apply(length);
    }

}
