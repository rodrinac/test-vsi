package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AnagramHelperTest {

    @Test
    void givenNullWordShouldReturnEmptySet() {
        final Set<String> anagrams = AnagramHelper.getAnagrams(null);

        assertTrue(anagrams.isEmpty());
    }

    @Test
    void givenEmptyWordShouldReturnEmptySet() {
        final Set<String> anagrams = AnagramHelper.getAnagrams("");

        assertTrue(anagrams.isEmpty());
    }

    @Test
    void givenBlankWordShouldReturnEmptySet() {
        final Set<String> anagrams = AnagramHelper.getAnagrams("  ");

        assertTrue(anagrams.isEmpty());
    }

    @Test
    void givenInvalidWordShouldProduceEmptySet() {
        final Set<String> result = AnagramHelper.getAnagrams("nw4u%@5b8  ");

        assertTrue(result.isEmpty());
    }

    @Test
    void givenOneCharWordShouldReturnSingletonSet() {
        final Set<String> anagrams = AnagramHelper.getAnagrams("a");

        assertEquals(Collections.singleton("a"), anagrams);
    }

    @Test
    void givenValidWordShouldProduceAnagrams() {
        final Set<String> expected = new HashSet<>(Arrays.asList("ocw", "wco", "cow", "woc", "cwo", "owc"));
        final Set<String> result = AnagramHelper.getAnagrams("cow");

        assertEquals(expected, result);
    }
}
