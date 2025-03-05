package org.example;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Helper class that generates anagrams.
 */
public class AnagramHelper {

    // Patter to match words
    private static final Pattern WORD_PATTERN = Pattern.compile("^[a-zA-Z]*$", Pattern.MULTILINE);

    private AnagramHelper() {

    }

    // Accepts a word to generate anagrams
    public static Set<String> getAnagrams(final String word) {

        // Validates if it isn't empty or not a valida word
        if (word == null || !WORD_PATTERN.matcher(word).matches()) {
            return Collections.emptySet();
        }

        return generateAnagrams(word.toCharArray());
    }

    private static Set<String> generateAnagrams(final char[] chars) {
        final Set<String> anagrams = new HashSet<>();
        final int n = chars.length;
        final int[] indices = new int[n]; // keeps track of each anagram

        // The word itself is an anagram
        anagrams.add(new String(chars));

        int i = 0;
        while (i < n) {
            if (indices[i] < i) {
                // Swap based on whether 'i' is even or odd
                if (i % 2 == 0) {
                    swap(chars, 0, i);
                } else {
                    swap(chars, indices[i], i);
                }

                // add the new permutation
                anagrams.add(new String(chars));

                // Increment the index for this level
                indices[i]++;

                // Reset i to start over
                i = 0;
            } else {
                // reset the index and move to next level
                indices[i] = 0;
                i++;
            }
        }

        return anagrams;
    }

    // Swapping the characters in the array to get anagrams
    private static void swap(final char[] arr, final int i, final int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
