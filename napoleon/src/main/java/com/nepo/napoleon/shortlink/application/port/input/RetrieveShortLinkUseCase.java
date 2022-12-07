package com.nepo.napoleon.shortlink.application.port.input;

import com.nepo.napoleon.shortlink.domain.ShortLink;


public interface RetrieveShortLinkUseCase {
    ShortLink retrieveShortLinkById(final String id);
}
