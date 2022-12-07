package com.nepo.napoleon.shortlink.utils;


import org.apache.commons.lang3.RandomStringUtils;

public class RandomAlphanumericGenerator {
    private RandomAlphanumericGenerator(){
        throw new IllegalStateException("Utility Class");
    }
    public static String randomAlphanumeric(final int length){
        return RandomStringUtils.random(length, true, true);
    }
}
