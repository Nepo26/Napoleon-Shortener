package com.nepo.napoleon.shortlink.application.port.output;

import com.nepo.napoleon.shortlink.domain.ShortLink;

import java.util.Collection;

public interface ListShortLinkPort {
    Collection<ShortLink> list();
}
