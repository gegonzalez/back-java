package com.example.application.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PromotionUtilTest {
    private static int PRICE = 1000;

    @Test
    void shouldCalculateForOneRepetion() {
        String word = " casa ";
        var result = PromotionUtil.calculateDiscount(word, PRICE);
        assertNotNull(result);
        assertEquals(100, result.getAmount());
        assertEquals(10, result.getPercentage());
    }

    @Test
    void shouldCalculateForMoreThanFiveRepetions() {
        String word = "Juego Red Dead Redemption II j";
        var result = PromotionUtil.calculateDiscount(word, PRICE);
        assertNotNull(result);
        assertEquals(500, result.getAmount());
        assertEquals(50, result.getPercentage());
    }

    @Test
    void shouldCalculateForNoneRepetions() {
        String word = "abcdefghijk";
        var result = PromotionUtil.calculateDiscount(word, PRICE);
        assertNotNull(result);
        assertEquals(1000, result.getAmount());
        assertEquals(0, result.getPercentage());
    }
}