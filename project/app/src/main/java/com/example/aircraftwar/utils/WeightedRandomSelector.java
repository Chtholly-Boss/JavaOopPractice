package com.example.aircraftwar.utils;

import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.Random;
import java.util.TreeMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Map the object to the Cummulated Function
 * @param <T> : Object to be generated
 */
public  class WeightedRandomSelector<T> {
    private final NavigableMap<Double, Supplier<T>> weightedMap;
    private final Random random;
    private double maxProb = 1.0;

    public WeightedRandomSelector(Map<Supplier<T>, Double> objectProbabilities) {
        weightedMap = createWeightedMap(objectProbabilities);
        random = new Random();
    }

    private static <T> NavigableMap<Double, Supplier<T>> createWeightedMap(Map<Supplier<T>, Double> objectProbabilities) {
        return objectProbabilities.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getValue,
                        Map.Entry::getKey,
                        (a, b) -> b,
                        TreeMap::new
                ));
    }

    public T selectRandomObject() {
        double randomValue = random.nextDouble() * maxProb;
        return weightedMap.ceilingEntry(randomValue) == null ? null : weightedMap.ceilingEntry(randomValue).getValue().get();
    }

    public void setMaxProb(double maxProb) {
        this.maxProb = maxProb;
    }
}
