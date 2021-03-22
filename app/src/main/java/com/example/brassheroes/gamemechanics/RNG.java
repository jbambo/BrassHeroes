package com.example.brassheroes.gamemechanics;

import java.util.Random;

import static com.example.brassheroes.R.drawable.enemy1;
import static com.example.brassheroes.R.drawable.enemy2;
import static com.example.brassheroes.R.drawable.enemy3;
import static com.example.brassheroes.R.drawable.enemy4;
import static com.example.brassheroes.R.drawable.enemy5;


public class RNG {
    private static final String[] damageTypes = {
            "magic",
            "mental",
            "physical"
    };

    private static final String[] firstNames = {
            "Leo",
            "Fyodor",
            "Friedrich",
            "Arthur",
            "Wolfgang"};
    private static final String[] lastNames = {
            "Tolstoy",
            "Dostoyevsky",
            "Nietzsche",
            "Schopenhauer",
            "von Goethe"};

    private static final int[] enemyPictures = new int[]{enemy1, enemy2, enemy3, enemy4, enemy5};

    public static String randomName() {
        return firstNames[randomNumber(firstNames.length, 0)] + " "
                + lastNames[randomNumber(lastNames.length, 0)];
    }

    public static int randomNumber(int range, int start) {
        Random r = new Random();
        return r.nextInt(range) + start;
    }

    public static int randomEnemyPortrait() {
        return enemyPictures[randomNumber(enemyPictures.length, 0)];
    }

    public static String randomDamageType() {
        return damageTypes[randomNumber(damageTypes.length, 0)];
    }

}
