package com.nepo.napoleon.shortlink.domain;

import lombok.Getter;
import lombok.Setter;


public class Key {
    @Getter
    @Setter
    String keyValue;

    public Key(final String keyValue){
        this.keyValue = keyValue;
    }
}
