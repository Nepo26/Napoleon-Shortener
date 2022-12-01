package br.com.nepo.napoleonshortener.shortlink.application.port.output;

import br.com.nepo.napoleonshortener.shortlink.domain.ShortLink;

import java.util.Collection;

public interface ListShortLinkPort {
    Collection<ShortLink> list();
}
