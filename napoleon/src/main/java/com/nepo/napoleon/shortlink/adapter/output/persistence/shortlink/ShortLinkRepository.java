package com.nepo.napoleon.shortlink.adapter.output.persistence.shortlink;

import com.nepo.napoleon.shortlink.domain.ShortLink;

import java.util.Collection;

public interface ShortLinkRepository {
    void save(ShortLinkEntity shortLink) ;
    Collection<ShortLink> list();
    ShortLink retrieve(String id);
}
