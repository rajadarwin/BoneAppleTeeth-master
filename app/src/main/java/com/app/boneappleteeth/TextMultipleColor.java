package com.app.boneappleteeth;

public class TextMultipleColor {

    public static String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }
}
