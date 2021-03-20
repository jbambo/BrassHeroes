package com.example.brassheroes.fightmechanics;

import java.util.Random;

public class RNG {
    private static final String[] suffixes = {
            "the Mighty",
            "the Righteous",
            "the Noble"};
    private static final String[] firstNames = {
            "Connor",
            "Leo",
            "Fyodor",
            "Christopher",
            "Friedrich",
            "Arthur",
            "Wolfgang"};
    private static final String[] lastNames = {
            "McGregor",
            "Tolstoy",
            "Nolan",
            "Dostoyevsky",
            "Nietzsche",
            "Schopenhauer",
            "von Goethe"};

    public static String randomName() {
        return suffixes[randomNumber(suffixes.length, 0)] + " "
                + firstNames[randomNumber(firstNames.length, 0)] + " "
                + lastNames[randomNumber(lastNames.length, 0)];
    }

    public static int randomNumber(int range, int start) {
        Random r = new Random();
        return r.nextInt(range) + start;
    }

}
