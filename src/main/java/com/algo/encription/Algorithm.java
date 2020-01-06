package com.algo.encription;

public class Algorithm {
    /**
     * An English text needs to be encrypted using the following encryption scheme.
     * First, the spaces are removed from the text. Let L be the length of this text.
     * Then, characters are written into a grid, whose rows and columns have the following constraints:
     * floor(sqrt(L)) <= row <= column <= floor(sqrt(L))
     * The encoded message is obtained by displaying the characters in a column,
     * inserting a space, and then displaying the next column and inserting a space, and so on.
     * <p>
     * Example:
     * For the text:
     * "if man was meant to stay on the ground god would have given us roots"
     * we build the following grid:
     * <p>
     * ifmanwas
     * meanttos
     * tayonthe
     * groundgo
     * dwouldha
     * vegivenu
     * sroots
     * <p>
     * and extract encrypted text from it:
     * imtgdvs fearwer mayoogo anouuio ntnnlvt wttddes aohghn sseoau
     */
    static String encryption(String s) {
        String withoutWhitespaces = s.replaceAll("\\s+", "");
        char[] characters = withoutWhitespaces.toCharArray();
        int step = (int) Math.ceil(Math.sqrt(characters.length));
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < step; index++) {
            int currentIndex = index;
            while (currentIndex < characters.length) {
                builder.append(characters[currentIndex]);
                currentIndex += step;
            }
            builder.append(" ");
        }
        return builder.toString();
    }
}
