package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HomeWorkTests {
    /***
     *                      5
     *                     / \
     *                    3   7
     *                   | \  | \
     *                  2  4  6  9
     *                 /         | \
     *                1         8   10
     */
    private static final HomeWork tree = new HomeWork();

    @BeforeAll
    public static void beforeAll() {
        tree.add(5);

        tree.add(3);
        tree.add(2);
        tree.add(4);
        tree.add(1);

        tree.add(7);
        tree.add(6);
        tree.add(9);
        tree.add(8);
        tree.add(10);
    }

    @ParameterizedTest
    @MethodSource("validArguments")
    public void should_findMaxDigitsValid(int count, int upperBound, List<Integer> expected) {
        List<Integer> result = tree.findMaxDigits(count, upperBound);

        assertEquals(expected, result);
    }

    private static Stream<Arguments> validArguments() {
        return Stream.of(
                Arguments.of(3, 4, List.of(4, 3, 2)),
                Arguments.of(1, 1, List.of(1)),
                Arguments.of(10, 10, List.of(10, 9, 8, 7, 6, 5, 4, 3, 2, 1))
        );
    }

    @Test
    public void when_UpperBoundGreaterMaxValue_then_ListStartsFromMaxValue() {
        int count = 3;
        int upperBound = 100;

        List<Integer> result = tree.findMaxDigits(count, upperBound);

        assertEquals(List.of(10, 9, 8), result);
    }

    @Test
    public void when_countLessZero_then_throwIllegalArgumentException() {
        int count = -1;
        int upperBound = 10;

        assertThrows(IllegalArgumentException.class, () -> tree.findMaxDigits(count, upperBound));
    }

    @Test
    public void when_upperBoundLessZero_then_throwIllegalArgumentException() {
        int count = 3;
        int upperBound = -1;

        assertThrows(IllegalArgumentException.class, () -> tree.findMaxDigits(count, upperBound));
    }

}
