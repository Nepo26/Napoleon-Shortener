package br.com.nepo.napoleonshortener.shortlink.application.port.input;

import br.com.nepo.napoleonshortener.shortlink.domain.ShortLink;

public interface CreateShortLinkUseCase {
    ShortLink createShortLink(CreateShortLinkCommand createShortLinkCommand);
}
