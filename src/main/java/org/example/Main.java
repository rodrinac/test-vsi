package org.example;

public class Main {
    public static void main(String[] args) {
        final String word = args.length > 0 ? args[0] : "babana";
        System.out.printf("Anagrams for %s are: %s%n", word, AnagramHelper.getAnagrams(word));
    }
}
