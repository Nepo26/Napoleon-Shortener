package com.nepo.napoleon.shortlink.application.port.output;

import com.nepo.napoleon.shortlink.domain.ShortLink;

public interface CreateShortLinkPort {
    void create(ShortLink shortLink);
}
