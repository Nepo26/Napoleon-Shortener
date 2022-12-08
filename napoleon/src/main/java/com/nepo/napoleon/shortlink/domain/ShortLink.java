package com.nepo.napoleon.shortlink.domain;

import lombok.Getter;

import java.util.Objects;

public class ShortLink {
    @Getter
    private final String randomId;

    @Getter
    private final String link;


    public ShortLink(final String randomId, final String link) {
        this.randomId = randomId;
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShortLink shortLink)) return false;
        return getRandomId().equals(shortLink.getRandomId()) && getLink().equals(shortLink.getLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRandomId(), getLink());
    }
}
