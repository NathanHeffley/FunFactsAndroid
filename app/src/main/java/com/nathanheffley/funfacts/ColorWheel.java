package com.nathanheffley.funfacts;

import android.graphics.Color;

import java.util.Random;

public class ColorWheel {
    // Color control variables
    private static int currentColor = 0;
    private static String[] colorsList = {
            "#39add1", // light blue
            "#3079ab", // dark blue
            "#c25975", // mauve
            "#e15258", // red
            "#f9845b", // orange
            "#838cc7", // lavender
            "#7d669e", // purple
            "#53bbb4", // aqua
            "#51b46d", // green
            "#e0ab18", // mustard
            "#637a91", // dark gray
            "#f092b0", // pink
            "#b7c0c7"  // light gray
    };

    // Random number generator for selecting colors
    private static Random randomGenerator = new Random();

    public static int getColor() {
        // Select a random color index that is different from currentColor
        int randomNumber;
        do {
            randomNumber = randomGenerator.nextInt(colorsList.length);
        } while(currentColor == randomNumber);

        // Set the currentColor to the new color index
        currentColor = randomNumber;

        // Parse the color code and return it
        return Color.parseColor(colorsList[currentColor]);
    }
}
