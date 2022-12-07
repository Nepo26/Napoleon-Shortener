package com.nepo.keyprovider.key.domain;

import lombok.Getter;


public class Key {
    @Getter
    String keyValue;

    public Key(final String keyValue){
        this.keyValue = keyValue;
    }
}
