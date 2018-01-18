package com.modeling.lab3.constants;

import com.modeling.lab3.entities.DefaultGenerator;
import com.modeling.lab3.entities.Generator;
import com.modeling.lab3.entities.LemerGenerator;

import java.util.function.Function;

/**
 * Перечисление типов генераторов
 */
public enum GeneratorType {

    /**
     * Стандартный тип генератора
     */
    DEFAULT(DefaultGenerator::new),
    /**
     * Тип генератора методом Лемера
     */
    LEMER(LemerGenerator::new);

    /**
     * Создатель генератора
     */
    private final Function<Integer, Generator> generatorCreator;

    /**
     * Конструктор типа генератора
     *
     * @param generatorCreator создатель генератора
     */
    GeneratorType(final Function<Integer, Generator> generatorCreator) {
        this.generatorCreator = generatorCreator;
    }

    /**
     * Создание генератора последовательности псевдослучайных числе заданной длины
     *
     * @param length длина последовательности
     * @return генератор
     */
    public Generator createGenerator(final int length) {
        return generatorCreator.apply(length);
    }

}
