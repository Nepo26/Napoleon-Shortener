package com.nepo.napoleon.shortlink.application.port;

import com.nepo.napoleon.shortlink.adapter.output.persistence.key.KeyClient;
import com.nepo.napoleon.shortlink.application.port.input.CreateShortLinkCommand;
import com.nepo.napoleon.shortlink.application.port.input.CreateShortLinkUseCase;
import com.nepo.napoleon.shortlink.application.port.input.ListShortLinkUseCase;
import com.nepo.napoleon.shortlink.application.port.input.RetrieveShortLinkUseCase;
import com.nepo.napoleon.shortlink.application.port.output.CreateShortLinkPort;
import com.nepo.napoleon.shortlink.application.port.output.ListShortLinkPort;
import com.nepo.napoleon.shortlink.application.port.output.RetrieveShortLinkPort;
import com.nepo.napoleon.shortlink.domain.ShortLink;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.Objects;

import static com.nepo.napoleon.shortlink.utils.RandomAlphanumericGenerator.randomAlphanumeric;


@RequiredArgsConstructor
@Service
@Slf4j
//@CacheConfig(cacheNames = "shortlink-cache")
public class ShortLinkService implements CreateShortLinkUseCase, ListShortLinkUseCase, RetrieveShortLinkUseCase {

    private final CreateShortLinkPort createShortLinkPort;
    private final ListShortLinkPort listShortLinkPort;
    private final RetrieveShortLinkPort retrieveShortLinkPort;

    private final KeyClient keyClient;

    @Override
    public ShortLink createShortLink(final CreateShortLinkCommand createShortLinkCommand) throws MalformedURLException, URISyntaxException {
        // Check if it's a valid URI
        new URL(createShortLinkCommand.getLink()).toURI();

        final String randomId = Objects.requireNonNull(keyClient.getRandomKey().getBody()).getKeyValue();

        final ShortLink shortLink = new ShortLink(randomId, createShortLinkCommand.getLink());

        createShortLinkPort.create(shortLink);

        return shortLink;
    }

    @Override
    public Collection<ShortLink> listShortLinks(final CreateShortLinkCommand createShortLinkCommand) {
        return listShortLinkPort.list();
    }

    @Override
//    @Cacheable
    public ShortLink retrieveShortLinkById(final String id) {
        return retrieveShortLinkPort.retrieve(id);
    }
}
