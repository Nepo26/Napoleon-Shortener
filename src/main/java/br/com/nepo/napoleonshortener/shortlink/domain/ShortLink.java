package br.com.nepo.napoleonshortener.shortlink.domain;

import lombok.Getter;

public class ShortLink {
    @Getter
    private final String randomId;

    @Getter
    private final String link;


    public ShortLink(final String randomId, final String link) {
        this.randomId = randomId;

        this.link = link;
    }

}
