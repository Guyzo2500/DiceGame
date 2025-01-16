package fr.mbds.dicegame;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Dice {
    private final Random random = new Random();

    public int roll() {
        return random.nextInt(6) + 1; // Génère un nombre entre 1 et 6
    }
}
