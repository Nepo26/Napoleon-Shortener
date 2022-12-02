package br.com.nepo.napoleonshortener.shortlink.application.port.output;

import br.com.nepo.napoleonshortener.shortlink.domain.ShortLink;


public interface RetrieveShortLinkPort {
    ShortLink retrieve(String id);
}
