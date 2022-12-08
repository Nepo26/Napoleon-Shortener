package com.nepo.napoleon.shortlink.application.port.input;

import com.nepo.napoleon.shortlink.domain.ShortLink;

import java.util.Collection;

public interface ListShortLinkUseCase {
    Collection<ShortLink> listShortLinks(CreateShortLinkCommand createShortLinkCommand);
}
