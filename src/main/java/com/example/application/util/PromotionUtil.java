package com.example.application.util;

import com.example.application.domain.Discount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class PromotionUtil {

    public static final int MAX_PERCENTAGE_INT = 5;
    private static final Logger LOG = LoggerFactory.getLogger(PromotionUtil.class);

    public static Discount calculateDiscount(String description, int price) {
        LOG.info(String.format("method=calculateDiscount description=%s price=%d", description, price));
        if (null == description || description.isBlank() || price <= 0) {
            return buildNoDiscount(price);
        }
        var percentage = 50;
        var repeatedLetters = countRepeatedLetters(description);
        if (repeatedLetters <= 0) {
            return buildNoDiscount(price);
        }
        if(repeatedLetters <= MAX_PERCENTAGE_INT) {
            percentage = repeatedLetters * 10;
        }
        var discountAmount = (int) (price * (percentage / 100.0f));
        return new Discount(discountAmount, percentage);
    }

    private static Discount buildNoDiscount(int price) {
        return new Discount(price, 0);
    }

    private static int countRepeatedLetters(String word) {
        Map<Character, Integer> countOfLetters = new HashMap<>();
        var wordInLowerCase = word.toLowerCase().replace(" ", "");
        for(int i=0; i < wordInLowerCase.length(); i++) {
            if (countOfLetters.containsKey(wordInLowerCase.charAt(i))) {
                countOfLetters.replace(wordInLowerCase.charAt(i), countOfLetters.get(wordInLowerCase.charAt(i)) + 1);
            } else {
                countOfLetters.put(wordInLowerCase.charAt(i), 1);
            }
        }
        return (int) countOfLetters.values().stream().filter(counted -> counted > 1).mapToInt(i -> i).count();
    }
}
