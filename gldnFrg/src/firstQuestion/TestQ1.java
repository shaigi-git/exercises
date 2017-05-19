package firstQuestion;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestQ1 {

    private void execute(String expected, String input) {
        assertEquals(expected, new String(Reverser.reverse(input.toCharArray())));
    }

    private void executeTwice(String expectedAndInput) {
        String reversed      = new String(Reverser.reverse(expectedAndInput.toCharArray()));
        String reversedTwice = new String(Reverser.reverse(reversed.toCharArray()));
        assertEquals(expectedAndInput, reversedTwice);
    }

    @Test
    public void testReverse() {
        execute("?pUs'tAhwyEh", "HeyWhat'sUp?");
        execute("pOnmlkjIhgfEdcbA", "abcdefghijklmnop");
        execute("EdUdkcEhEhttAhw", "WhatTheHeckDude");
        execute("whAtthEfUckdUdE", "eDuDkCuFeHtTaHw");
        execute("AAAIIIOUE", "euoiiiaaa");
        execute("@xx", "Xx@");
        execute("UOIEA4 3 2 1 ", " 1 2 3 4aeiou");
        execute("    ", "    ");
        execute("   E ", " E   ");
        execute("   E ", " e   ");
        execute("  m  ", "  M  ");
        execute(".gnIhtynA dEEn yEht fI EEs Ot AnImA htIw nI kcEhc Ot ErUs Eb",
                "Be sure to check in with Amina to see if they need anything.");
    }

    @Test
    public void testReverseTwice() {
        executeTwice("AbcdE");
        executeTwice("pOnmlkjIhgfEdcbA");
        executeTwice("EdUdkcUfEhttAhw");
        executeTwice("whAtthEfUckdUdE");
    }
}
