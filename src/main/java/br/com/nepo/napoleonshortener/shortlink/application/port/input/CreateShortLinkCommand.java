package br.com.nepo.napoleonshortener.shortlink.application.port.input;

import lombok.Getter;

import javax.validation.constraints.NotNull;

public class CreateShortLinkCommand {
    @Getter
    @NotNull
    String link;


    public CreateShortLinkCommand(final String link) {
        this.link = link;
    }
}
