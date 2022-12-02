package br.com.nepo.napoleonshortener.shortlink.adapter.output.persistence;

import br.com.nepo.napoleonshortener.shortlink.domain.ShortLink;

import java.util.Collection;

public interface ShortLinkRepository {
    void save(ShortLinkEntity shortLink) ;
    Collection<ShortLink> list();
    ShortLink retrieve(String id);
}
