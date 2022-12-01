package br.com.nepo.napoleonshortener.shortlink.application.port.input;

import br.com.nepo.napoleonshortener.shortlink.domain.ShortLink;

import java.util.Collection;

public interface ListShortLinkUseCase {
    Collection<ShortLink> listShortLinks(CreateShortLinkCommand createShortLinkCommand);
}
