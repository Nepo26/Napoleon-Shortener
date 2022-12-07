package com.nepo.napoleon.shortlink.application.port.input;

import com.nepo.napoleon.shortlink.domain.ShortLink;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public interface CreateShortLinkUseCase {
    ShortLink createShortLink(CreateShortLinkCommand createShortLinkCommand) throws MalformedURLException, URISyntaxException;
}
